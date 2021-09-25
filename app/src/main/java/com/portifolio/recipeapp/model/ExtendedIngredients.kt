package com.portifolio.recipeapp.model

import com.google.gson.annotations.SerializedName

data class ExtendedIngredients (

    @SerializedName("dairyFree")
    val amount: Double,

    @SerializedName("extendedIngredients")
    val consistency: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("original")
    val original: String,

    @SerializedName("unit")
    val unit: String,

        )