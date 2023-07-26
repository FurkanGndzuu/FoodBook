package com.frkn.foodsbook.services

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SpecialSharedPreferences {

    companion object{
        private var sharedPreferences : SharedPreferences? = null
        private val ZAMAN = "zaman"

        @Volatile private var instance : SpecialSharedPreferences? = null
        private var lock = Any()

        operator  fun invoke(context: Context) : SpecialSharedPreferences = instance ?: synchronized(
            lock){
            instance ?: SpecialSharedPreferences(context).also {
                instance = it
            }
        }
    }

    fun CreateSpecialSharedPreferences(context: Context) : SpecialSharedPreferences {

        sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        return SpecialSharedPreferences()

    }

    fun SaveTime(time: Long){
        sharedPreferences?.edit(commit = true){
            putLong(ZAMAN,time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(ZAMAN,0)

}