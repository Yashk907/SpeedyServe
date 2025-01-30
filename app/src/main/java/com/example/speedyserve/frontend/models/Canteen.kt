package com.example.speedyserve.frontend.models

import androidx.compose.runtime.ComposableInferredTarget
import kotlinx.serialization.Serializable

@Serializable
data class Canteen(
    val _id : String,
    val name : String,
//    val password : String,
    val listDish : List<Dish>,
//    val mobile : String,
    val openingTime : String,
    val closingTime : String,
//    val emailId : String

)

