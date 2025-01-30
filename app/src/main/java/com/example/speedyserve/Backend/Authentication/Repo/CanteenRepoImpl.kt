package com.example.speedyserve.Backend.Authentication.Repo

import com.example.speedyserve.Backend.Authentication.Api.AuthApi
import com.example.speedyserve.Backend.Authentication.Api.DishResponse
import com.example.speedyserve.Backend.Authentication.Api.req
import com.example.speedyserve.Backend.Authentication.Models.Canteen
import com.example.speedyserve.Backend.Authentication.Models.Dish
import javax.inject.Inject


class CanteenRepoImpl(private val api: AuthApi) : CanteenRepo{
    override suspend fun getCanteens(): List<Canteen> {
        return api.getCanteenList()
    }

    override suspend fun getDishes(_id: String): DishResponse {
        val body = mapOf("canteenId" to _id)
        return api.getMenuList(body)
    }

}