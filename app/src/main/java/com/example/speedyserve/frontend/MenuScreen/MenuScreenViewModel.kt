package com.example.speedyserve.frontend.MenuScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedyserve.Backend.Authentication.Models.Dish
import com.example.speedyserve.Backend.Authentication.Models.OrderDish
import com.example.speedyserve.Backend.Authentication.Repo.CanteenRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuScreenViewModel @Inject constructor(private val repoImpl: CanteenRepoImpl,
    private val savedStateHandle: SavedStateHandle): ViewModel() {


    private val _orderList = mutableListOf<OrderDish>()
    val orderList = _orderList

    private val currentDishId = mutableStateOf("")
     val isLoading = mutableStateOf(true)


    private val _Menu = MutableStateFlow<List<Dish>>(emptyList<Dish>())
    val Menu = _Menu



init {
    val _id =savedStateHandle.get<String>("id")
    Log.d("check",_id.toString())
if(_id!=null){
    onEvent(MenuScreenActions.fetchMenu(_id))
    isLoading.value=true
}
}


    fun onEvent(event : MenuScreenActions){
        when(event){
            is MenuScreenActions.fetchMenu -> {
                viewModelScope.launch{
                    val fetchMenu = repoImpl.getDishes(event._id).dishes
                    if(fetchMenu.isNotEmpty()){
                        _Menu.value=fetchMenu
                    }
                }

            }

            is MenuScreenActions.IncreaseQuantity -> {
                for(i in 0..<orderList.size){
                    if(currentDishId.value==orderList[i].foodId){
                        _orderList[i].quantity.inc()
                    }
                }
            }

            is MenuScreenActions.DecreaseQuantity -> {for(i in 0..<orderList.size){
                if(currentDishId.value==orderList[i].foodId){
                    if(_orderList[i].quantity.dec()>=0){
                        _orderList[i].quantity.dec()
                    }
                }
            }
            }
            is MenuScreenActions.addDish -> {
                currentDishId.value=event.Dish.foodId
                viewModelScope.launch{
                    _orderList.add(OrderDish(
                        event.Dish.foodId,
                        event.Dish.quantity.inc(),
                        event.Dish.price
                    ))
                }

            }
        }
    }

}

