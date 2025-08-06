package com.example.hospitalapp.models

import androidx.core.app.GrammaticalInflectionManagerCompat.GrammaticalGender

data class Patient(
    val id:String? = null,
    val name:String? = null,
    val gender: String? = null,
    val nationality:String? = null,
    val age: String? = null,
    val diagnosis: String? = null,
    val imageUrl: String? = null
)
