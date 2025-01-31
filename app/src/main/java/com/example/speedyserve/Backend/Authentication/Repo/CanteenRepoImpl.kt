package com.example.speedyserve.Backend.Authentication.Repo

import android.util.Log
import com.example.speedyserve.Backend.Authentication.Api.AuthApi
import com.example.speedyserve.Backend.Authentication.Api.DishResponse
import com.example.speedyserve.Backend.Authentication.Api.orderSchema
import com.example.speedyserve.Backend.Authentication.Api.req
import com.example.speedyserve.Backend.Authentication.Models.Canteen
import com.example.speedyserve.Backend.Authentication.Models.Dish
import com.example.speedyserve.Backend.Authentication.Models.OrderDish
import javax.inject.Inject


class CanteenRepoImpl(private val api: AuthApi) : CanteenRepo{
    private var _orderedDishListRepo = listOf<OrderDish>()
    var orderDishList = _orderedDishListRepo

    suspend fun updateList(orderList : List<OrderDish>){
        _orderedDishListRepo=orderList
        Log.d("repocheck",orderDishList.toString())
        Log.d("repocheck1",orderList.toString())
        Log.d("repocheck2",_orderedDishListRepo.toString())
        orderDishList=_orderedDishListRepo
    }
    override suspend fun getCanteens(): List<Canteen> {
        return api.getCanteenList()
    }

    override suspend fun getDishes(_id: String): DishResponse {
        val body = mapOf("canteenId" to _id)
        return api.getMenuList(body)
    }

    override suspend fun placeOrder(orderSchema: orderSchema) {
        return api.placeOrder(orderSchema)
    }

    override suspend fun deleteList() {
        _orderedDishListRepo=emptyList()
    }

}