package com.frkn.foodsbook.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frkn.foodsbook.abstractions.FoodDatabase
import com.frkn.foodsbook.model.Food
import com.frkn.foodsbook.services.FoodAPIService
import com.frkn.foodsbook.services.SpecialSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class FoodListViewModel(application: Application) : BaseViewModel(application) {
    val Foods = MutableLiveData<List<Food>>();
    val FoodsErrorMessage = MutableLiveData<Boolean>();
    val FoodsIsUploading = MutableLiveData<Boolean>();

    private var specialSharedPreferences  =  SpecialSharedPreferences()

    private val updateTime = 10 * 60 *1000 * 1000 * 1000L


    private val FoodApiService = FoodAPIService()
    private val disposable = CompositeDisposable();


    fun refreshData(){
       var savedTime = specialSharedPreferences.getTime()
        if(savedTime != null && savedTime !=0L && System.nanoTime() - savedTime > updateTime){
            getDatasFromSqlLite()
        }
        else{
            getDatasFromInternet()
        }
    }

    fun getDatasFromSqlLite(){
        launch {
           var foodListSql =  FoodDatabase(getApplication()).foodDao().getAllBesin()
            getFoods(foodListSql)
            Toast.makeText(getApplication() , "Sql" , Toast.LENGTH_LONG).show()
        }
    }


    fun getDatasFromInternet(){

        FoodsIsUploading.value = true


        disposable.add(FoodApiService.getDatas()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Food>>(){
                override fun onSuccess(t: List<Food>) {

                    getFoods(t)



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
            while (i<t.size){
                t[i].uuid = idList[i].toInt()
                i++
            }

            getFoods(t)
        }

       specialSharedPreferences.SaveTime(System.nanoTime())
    }
}