package com.frkn.foodsbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.frkn.foodsbook.R
import com.frkn.foodsbook.model.Food
import com.frkn.foodsbook.util.UploadImage
import com.frkn.foodsbook.util.placeHolder
import com.frkn.foodsbook.view.FoodDetailsDirections
import com.frkn.foodsbook.view.FoodListDirections
import java.util.zip.Inflater

class FoodListRecyclerView(val FoodList : ArrayList<Food>) : RecyclerView.Adapter<FoodListRecyclerView.FoodListHolder>() {
    class FoodListHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.food_list_recycler_row , parent , false)
        return FoodListHolder(view)
    }

    override fun getItemCount(): Int {
        return FoodList.size;
    }

    override fun onBindViewHolder(holder: FoodListHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.name).text = FoodList[position].name
        holder.itemView.findViewById<TextView>(R.id.kcal).text = FoodList[position].kcal
        holder.itemView.findViewById<ImageView>(R.id.imageView).UploadImage(FoodList[position].imageUrl , placeHolder(holder.itemView.context))
        holder.itemView.setOnClickListener {
            var action = FoodListDirections.actionFoodListToFoodDetails(45)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun UpdateFoodList(newFoodList: List<Food>){
        FoodList.clear()
        FoodList.addAll(newFoodList)
       notifyDataSetChanged()
    }
}