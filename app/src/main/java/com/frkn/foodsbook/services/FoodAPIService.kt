package com.frkn.foodsbook.services

import com.frkn.foodsbook.abstractions.FoodAPI
import com.frkn.foodsbook.model.Food
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class FoodAPIService {

    private val BASE_URL = "https://raw.githubusercontent.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(FoodAPI::class.java)

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
   fun getDatas() : Single<List<Food>> {
    val foods : Single<List<Food>> =   api.getFoods()
        return foods;
   }

}