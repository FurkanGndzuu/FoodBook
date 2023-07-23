package com.frkn.foodsbook.abstractions

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.frkn.foodsbook.model.Food

@Database(entities = arrayOf(Food::class) , version = 1)
abstract  class FoodDatabase : RoomDatabase() {
    abstract fun foodDao() : FoodDao

    companion object{
        @Volatile private var instance : FoodDatabase? = null

        private  var lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {

            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext
            , FoodDatabase::class.java , "FoodDatabase").build()

    }
}