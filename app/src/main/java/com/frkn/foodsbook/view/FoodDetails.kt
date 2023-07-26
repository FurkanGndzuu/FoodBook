package com.frkn.foodsbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.frkn.foodsbook.R
import com.frkn.foodsbook.util.UploadImage
import com.frkn.foodsbook.util.placeHolder
import com.frkn.foodsbook.viewModel.FoodDetailViewModel


class FoodDetails : Fragment() {


    private lateinit var  DetailViewModel : FoodDetailViewModel;

    private var foodId = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            println(FoodDetailsArgs.fromBundle(it).foodId)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            foodId = FoodDetailsArgs.fromBundle(it!!).foodId
        }

        DetailViewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)
        DetailViewModel.roomLiveData(foodId)

        observeLiveData()
    }


    fun observeLiveData(){
        DetailViewModel.food.observe(viewLifecycleOwner , Observer {
            f -> f?.let {
                view?.findViewById<TextView>(R.id.food_name)?.text = it.name
            view?.findViewById<TextView>(R.id.food_kcal)?.text = it.kcal
            view?.findViewById<TextView>(R.id.food_protein)?.text = it.protein
            view?.findViewById<TextView>(R.id.food_carbohydrate)?.text = it.carbonhydrate
            view?.findViewById<TextView>(R.id.food_oil)?.text = it.fat

            context?.let {
                c -> view?.findViewById<ImageView>(R.id.imageView)?.UploadImage(it.imageUrl , placeHolder(c))
            }
        }
        })
    }

}