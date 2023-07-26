package com.frkn.foodsbook.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frkn.foodsbook.abstractions.FoodDatabase
import com.frkn.foodsbook.model.Food
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application) : BaseViewModel(application) {
    val food  = MutableLiveData<Food>();

    fun roomLiveData(uuid : Int){
       launch {
          var dao = FoodDatabase(getApplication()).foodDao()
          var foodDao = dao.getFoodById(uuid)
           food.value = foodDao
       }
    }
}