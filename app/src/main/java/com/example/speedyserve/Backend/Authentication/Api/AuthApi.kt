package com.example.speedyserve.Backend.Authentication.Api

import com.example.speedyserve.Backend.Authentication.Models.TokenResponse
import com.example.speedyserve.Backend.Authentication.Models.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/signUp")
    suspend fun signUp(@Body user : User)

    @POST("/api/signIn")
    suspend fun signIn(@Body user: User) : TokenResponse

}