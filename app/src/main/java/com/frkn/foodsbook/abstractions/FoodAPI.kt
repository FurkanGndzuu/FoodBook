package com.frkn.foodsbook.abstractions

import com.frkn.foodsbook.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getFoods() : Single<List<Food>>

}