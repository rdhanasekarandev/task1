package com.thamizhi.task1

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.thamizhi.task1.controller.AppController
import com.thamizhi.task1.data.Category
import com.thamizhi.task1.databinding.ActivityMainBinding
import com.thamizhi.task1.viewmodel.MainViewmodel
import java.lang.NullPointerException

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding:ActivityMainBinding
    lateinit var controller:AppController
    private val viewmodel:MainViewmodel by lazy {
        ViewModelProviders.of(this).get(MainViewmodel::class.java)
    }

    var categoryList= arrayListOf<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.viewmodel=viewmodel

        controller= AppController(this)


        binding.epoxyRecyclerView.setController(controller)

        viewmodel.result.observe(this, Observer {
            if(it.equals("ok")){
                viewmodel.categories.forEach {
                    val category = Category(it!!.id!!,it.categoryName!!,it.categoryImage!!,"\u20B9"+it.basePrice.toString(),true)
                    categoryList.add(category)
                }
                updateController(categoryList)
            }
        })
    }

    override fun onClick(v: View?) {

        val newlist= mutableListOf<Category>()

        var data=v!!.tag as Category
        var id=data.id


        categoryList.forEach {
            if(id==it.id)
            newlist.add(Category(it.id,it.name,it.image,it.price,false))
            else
            newlist.add(Category(it.id,it.name,it.image,it.price,true))
        }

        updateController(newlist)
    }

    fun updateController(data:List<Category>){


        controller.setData(data)
        updateUI()

    }

    fun updateUI() {

        binding.epoxyRecyclerView.post {

            binding.epoxyRecyclerView.getChildAt(0)
                .setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    val i = binding.epoxyRecyclerView.getChildAt(0).canScrollHorizontally(1)
                    val j = binding.epoxyRecyclerView.getChildAt(0).canScrollHorizontally(-1)
                    if (i) {
                        binding.imageRight.visibility = View.VISIBLE
                    } else {
                        binding.imageRight.visibility = View.GONE
                    }
                    if (j) {
                        binding.imageLeft.visibility = View.VISIBLE
                    } else {
                        binding.imageLeft.visibility = View.GONE
                    }

                }


            var w = binding.epoxyRecyclerView.getChildAt(0).width

            binding.imageRight.setOnClickListener {
                  binding.epoxyRecyclerView.getChildAt(0).scrollBy(w,0)
            }
                binding.imageLeft.setOnClickListener {
                    binding.epoxyRecyclerView.getChildAt(0).scrollBy(-w, 0)
                }
        }
    }
}