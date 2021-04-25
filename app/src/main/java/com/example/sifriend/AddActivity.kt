package com.example.sifriend

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class AddActivity : AppCompatActivity() {

    private  var mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var storage : FirebaseStorage
    private lateinit var db : FirebaseFirestore
    private var user = mAuth.currentUser
    var selectedPictureFromGallery : Uri? = null
    var selectedBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        mAuth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        db = FirebaseFirestore.getInstance()


        val postId = user.uid


        selectedImage.setOnClickListener {
            selectImage()
        }
        saveBtnAdd.setOnClickListener {
            var name = addNameText.text.toString()
            var age = ageText.text.toString()
            var konum = konumText.text.toString()
            var reason = reasonText.text.toString()
            sharePost(name,age,konum,reason,postId)
        }
    }

    private fun sharePost(name: String, age: String, konum: String, reason: String,postId:String) {

        val uuid = UUID.randomUUID()
        val imageName = "${uuid}.jpg"

        val reference = storage.reference

        val pictureReferance = reference.child("images").child(imageName)

        if (selectedPictureFromGallery != null) {
            pictureReferance.putFile(selectedPictureFromGallery!!).addOnSuccessListener { task ->
                val loadedPictureReferance =
                        FirebaseStorage.getInstance().reference.child("images").child(imageName)
                loadedPictureReferance.downloadUrl.addOnSuccessListener { uri ->
                    val url = uri.toString()
                    val date = Timestamp.now()

                    val post = hashMapOf(
                            "name" to name,
                            "age" to age,
                            "konum" to konum,
                            "reason" to reason,
                            "pictureUrl" to url,
                            "date" to date,
                            "postId" to postId,
                    )
                    db.collection("Posts").add(post).addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }.addOnFailureListener { e->
                        Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun selectImage() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //izni almamışız
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        } else {
            //izin zaten varsa
            val galeriIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriIntent,2)
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (requestCode == 1){
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //izin verilince yapılacaklar
                val galeriIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {

            selectedPictureFromGallery = data.data

            if (selectedPictureFromGallery != null) {

                if(Build.VERSION.SDK_INT >= 28) {

                    val source = ImageDecoder.createSource(this.contentResolver,selectedPictureFromGallery!!)
                    selectedBitmap = ImageDecoder.decodeBitmap(source)
                    selectedImage.setImageBitmap(selectedBitmap)

                } else {
                    selectedBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,selectedPictureFromGallery)
                    selectedImage.setImageBitmap(selectedBitmap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}