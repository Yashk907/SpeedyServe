package com.example.speedyserve.Backend.Authentication.Repo

import android.content.SharedPreferences
import android.util.Log
import com.example.speedyserve.Backend.Authentication.Api.AuthApi
import com.example.speedyserve.Backend.Authentication.Models.User

class AuthRepoImp(
    private val authApi: AuthApi,
    private val preferences: SharedPreferences
) : AuthRepo{
    override suspend fun signUp(user: User){
        try {
            return authApi.signUp(
                user
            )
        }catch (
            e : retrofit2.HttpException
        ){
            val errorBody = e.response()?.errorBody()?.string()
            Log.d("API Error", "Error: ${e.code()}, Body: $errorBody")
        }

    }

    override suspend fun signIn(user: User) {
        return try {
            val response=authApi.signIn(
                user
            )
            preferences.edit().putString("jwt",response.token)
                .apply()
        }
        catch (e : retrofit2.HttpException){
            val errors = e.response()?.errorBody()?.string()
        }
    }

}