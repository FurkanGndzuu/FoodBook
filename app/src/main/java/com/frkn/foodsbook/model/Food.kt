package com.frkn.foodsbook.model

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("isim")
    val name : String?,
    @SerializedName("kalori")
    val kcal: String? ,
    @SerializedName("karbonhidrat")
    val carbonhydrate : String ,
    @SerializedName("protein")
    val  protein : String? ,
    @SerializedName("yag")
    val fat : String? ,
    @SerializedName("gorsel")
val imageUrl : String?) {
}