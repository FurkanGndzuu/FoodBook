package com.frkn.foodsbook.abstractions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.frkn.foodsbook.model.Food

@Dao
interface FoodDao {

    @Insert
    suspend fun insertAll(vararg  food : Food) : List<Long>

    @Query("SELECT *FROM Food")
    suspend fun getAllBesin() : List<Food>

    @Query("Select *From Food Where uuid = :foodId")
    suspend fun getFoodById(foodId : Int) : Food

    @Query("Delete From Food")
    suspend fun deleteFoods()

}