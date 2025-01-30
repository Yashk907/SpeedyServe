package com.example.speedyserve.Backend.Authentication.Api

import com.example.speedyserve.Backend.Authentication.Models.TokenResponse
import com.example.speedyserve.Backend.Authentication.Models.User
import com.example.speedyserve.Backend.Authentication.Models.Canteen
import com.example.speedyserve.Backend.Authentication.Models.Dish
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("/api/signUp")
    suspend fun signUp(@Body user : User)

    @POST("/api/signIn")
    suspend fun signIn(@Body user: User) : TokenResponse

    @GET("/canteens/getCanteen")
    suspend fun getCanteenList() : List<Canteen>

    @POST("/canteens/getCanteenDishes")
    suspend fun getMenuList( @Body body: Map<String, String>) : DishResponse

}