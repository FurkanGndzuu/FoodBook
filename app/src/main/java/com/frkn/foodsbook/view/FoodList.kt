package com.frkn.foodsbook.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frkn.foodsbook.R
import com.frkn.foodsbook.adapter.FoodListRecyclerView
import com.frkn.foodsbook.viewModel.FoodListViewModel


class FoodList : Fragment() {

    private lateinit var foodLisViewModel : FoodListViewModel;
    private var recyclerView : FoodListRecyclerView = FoodListRecyclerView(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodLisViewModel = ViewModelProvider(this ).get(FoodListViewModel::class.java)
        foodLisViewModel.refreshData()

        view.findViewById<RecyclerView>(R.id.FoodListRecylerView).layoutManager = LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.FoodListRecylerView).adapter = recyclerView

        observeLiveData()
    }

    fun observeLiveData(){

        foodLisViewModel.Foods.observe(viewLifecycleOwner , Observer {
            it?.let {
                recyclerView.UpdateFoodList(it)
                view?.findViewById<RecyclerView>(R.id.FoodListRecylerView)?.visibility = View.VISIBLE }
        })

        foodLisViewModel.FoodsErrorMessage.observe(viewLifecycleOwner , Observer {
            it?.let{ b ->
                if(b){
                    view?.findViewById<TextView>(R.id.foodListPageErrorMessageText)?.visibility = View.VISIBLE
                }
                else{
                    view?.findViewById<TextView>(R.id.foodListPageErrorMessageText)?.visibility = View.GONE
                }
        }
        })


        foodLisViewModel.FoodsIsUploading.observe(viewLifecycleOwner , Observer {
            it?.let{ b ->
                if(b){
                    view?.findViewById<ProgressBar>(R.id.FoodListPageProgressBar)?.visibility = View.VISIBLE
                    view?.findViewById<RecyclerView>(R.id.FoodListRecylerView)?.visibility = View.GONE
                    view?.findViewById<TextView>(R.id.foodListPageErrorMessageText)?.visibility = View.GONE
                }
                else{
                    view?.findViewById<ProgressBar>(R.id.FoodListPageProgressBar)?.visibility = View.GONE
                }
            }
        })
    }
}