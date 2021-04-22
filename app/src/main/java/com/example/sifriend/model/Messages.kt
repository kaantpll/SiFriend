package com.example.sifriend.model

import com.google.firebase.auth.FirebaseUser

data class Messages(
        var message: String,
        var messageOwn: String?,
        var messageId: String,
)