package com.portifolio.recipeapp.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.portifolio.recipeapp.R

class RecipesRowBindingAdapter {

    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(view: ImageView, imageUrl: String){
            view.load(imageUrl){
                crossfade(600)
            }
        }

        @BindingAdapter("numberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(view: TextView, likes: Int){
              view.text = likes.toString()
        }

        @BindingAdapter("prepareInMinutes")
        @JvmStatic
        fun setPrepareInMinutes(view: TextView, minutes: Int){
            view.text = minutes.toString()
        }
        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, isVegan: Boolean){
            if (isVegan) when(view){
                is ImageView -> view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
                is TextView -> view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
            }
        }

    }


}