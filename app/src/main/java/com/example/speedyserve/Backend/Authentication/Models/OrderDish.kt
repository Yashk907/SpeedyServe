package com.example.speedyserve.Backend.Authentication.Models

data class OrderDish(
    val foodId: String,
    val quantity: Int =0,
    val price: String="0"
)
