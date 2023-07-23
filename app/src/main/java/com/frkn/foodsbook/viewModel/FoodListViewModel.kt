package com.frkn.foodsbook.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frkn.foodsbook.abstractions.FoodDatabase
import com.frkn.foodsbook.model.Food
import com.frkn.foodsbook.services.FoodAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class FoodListViewModel(application: Application) : BaseViewModel(application) {
    val Foods = MutableLiveData<List<Food>>();
    val FoodsErrorMessage = MutableLiveData<Boolean>();
    val FoodsIsUploading = MutableLiveData<Boolean>();


    private val FoodApiService = FoodAPIService()
    private val disposable = CompositeDisposable();


    fun refreshData(){
       getDatasFromInternet()
    }


    fun getDatasFromInternet(){

        FoodsIsUploading.value = true


        disposable.add(FoodApiService.getDatas()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Food>>(){
                override fun onSuccess(t: List<Food>) {

                }

                override fun onError(e: Throwable) {
                    FoodsIsUploading.value = false
                    FoodsErrorMessage.value = true
                    e.printStackTrace()
                }
            })
        )

    }

    private fun getFoods(t : List<Food>){
        Foods.value = t
        FoodsIsUploading.value = false
        FoodsErrorMessage.value = false
    }

   private fun SaveSqlLite(t : List<Food>){
        launch {
            val dao = FoodDatabase(context = getApplication()).foodDao()
            dao.deleteFoods()
            val idList = dao.insertAll(*t.toTypedArray())
            var i : Int = 0
            while (i<0){
                t[i].uuid = idList[i].toInt()
            }

            getFoods(t)
        }
    }
}