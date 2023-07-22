package com.frkn.foodsbook.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frkn.foodsbook.model.Food
import com.frkn.foodsbook.services.FoodAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class FoodListViewModel : ViewModel() {
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
                    Foods.value = t
                    FoodsIsUploading.value = false
                    FoodsErrorMessage.value = false
                }

                override fun onError(e: Throwable) {
                    FoodsIsUploading.value = false
                    FoodsErrorMessage.value = true
                    e.printStackTrace()
                }
            })
        )

    }
}