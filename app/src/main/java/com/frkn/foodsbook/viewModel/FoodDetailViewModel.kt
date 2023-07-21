package com.frkn.foodsbook.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frkn.foodsbook.model.Food

class FoodDetailViewModel : ViewModel() {
    val food  = MutableLiveData<Food>();

    fun roomLiveData(){
        val a : Food = Food("1" , "1","1" , "1","1" , "1" )
        food.value = a
    }
}