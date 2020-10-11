package com.thamizhi.task1.controller

import android.view.View
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.thamizhi.task1.CategoryItemBindingModel_
import com.thamizhi.task1.data.Category
import java.lang.RuntimeException

class AppController(onClickListener: View.OnClickListener) :TypedEpoxyController<List<Category>>(){

    var onClickListener=onClickListener

    override fun buildModels(data: List<Category>?) {

        val models=data!!.map {
            CategoryItemBindingModel_()
                .id(it.id)
                .category(it)
                .onClick(onClickListener)
                .imageUrl(it.image)
        }

        carousel {
            id("carousel")
            numViewsToShowOnScreen(3.5F)
            Carousel.setDefaultGlobalSnapHelperFactory(null)
            padding(Carousel.Padding.dp(0,4,0,8,16))
            models(models)
            hasFixedSize(false)
        }

    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
        throw exception
    }
}