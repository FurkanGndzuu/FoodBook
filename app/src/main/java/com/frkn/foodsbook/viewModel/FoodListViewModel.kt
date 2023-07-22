package com.frkn.foodsbook.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frkn.foodsbook.model.Food

class FoodListViewModel : ViewModel() {
    val Foods = MutableLiveData<List<Food>>();
    val FoodsErrorMessage = MutableLiveData<Boolean>();
    val FoodsIsUploading = MutableLiveData<Boolean>();


    fun refreshData(){
        val a : Food = Food("1" , "1","1" , "1","1" , "1" )
        val b : Food = Food("1" , "1","1" , "1","1" , "1" )
        val c : Food = Food("1" , "1","1" , "1","1" , "1" )

        var FoodListRefresData = arrayListOf(a , b ,c)
        Foods.value = FoodListRefresData
        FoodsErrorMessage.value = false
        FoodsIsUploading.value = false
    }
}