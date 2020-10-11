package com.thamizhi.task1

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view:ImageView,imageUrl:String?){
    Glide.with(view.context).load("http://134.209.47.247:4000/images/category/"+imageUrl).into(view)
}