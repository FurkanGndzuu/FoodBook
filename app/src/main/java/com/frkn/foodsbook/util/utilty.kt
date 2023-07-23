package com.frkn.foodsbook.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.frkn.foodsbook.R


fun ImageView.UploadImage(url : String? , placeholder: CircularProgressDrawable){

    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(findViewById(R.id.imageView))

}

fun placeHolder(context : Context) : CircularProgressDrawable{
    return  CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}