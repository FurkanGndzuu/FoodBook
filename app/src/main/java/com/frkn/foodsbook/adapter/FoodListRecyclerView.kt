package com.frkn.foodsbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.frkn.foodsbook.R
import com.frkn.foodsbook.abstractions.FoodClickListener
import com.frkn.foodsbook.databinding.FoodListRecyclerRowBinding
import com.frkn.foodsbook.model.Food
import com.frkn.foodsbook.util.UploadImage
import com.frkn.foodsbook.util.placeHolder
import com.frkn.foodsbook.view.FoodListDirections
import org.w3c.dom.Text

class FoodListRecyclerView(val FoodList : ArrayList<Food>) : RecyclerView.Adapter<FoodListRecyclerView.FoodListHolder>() , FoodClickListener {
    class FoodListHolder(var view : FoodListRecyclerRowBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListHolder {
        val inflater = LayoutInflater.from(parent.context)
        //var view = inflater.inflate(R.layout.food_list_recycler_row , parent , false)

        var view = DataBindingUtil.inflate<FoodListRecyclerRowBinding>(inflater , R.layout.food_list_recycler_row,parent ,false)
        return FoodListHolder(view)
    }

    override fun getItemCount(): Int {
        return FoodList.size;
    }

    override fun onBindViewHolder(holder: FoodListHolder, position: Int) {
        /*
                holder.itemView.findViewById<TextView>(R.id.name).text = FoodList[position].name
        holder.itemView.findViewById<TextView>(R.id.kcal).text = FoodList[position].kcal
        holder.itemView.findViewById<ImageView>(R.id.imageView).UploadImage(FoodList[position].imageUrl , placeHolder(holder.itemView.context))
        holder.itemView.setOnClickListener {
            var action = FoodListDirections.actionFoodListToFoodDetails(FoodList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
       */

        holder.view.foodXml = FoodList.get(position)
    }

    fun UpdateFoodList(newFoodList: List<Food>){
        FoodList.clear()
        FoodList.addAll(newFoodList)
       notifyDataSetChanged()
    }

    override fun FoodClick(view: View) {
        //val uuid = view.findViewById<TextView>(R.id.uuid_text).text.toString().toIntOrNull()

        //uuid?.let {
          // var action = FoodListDirections.actionFoodListToFoodDetails(it)
            //Navigation.findNavController(view).navigate(action)
        //}
    }
}