package com.portifolio.recipeapp.model

import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("aggregateLikes")
    val aggregateLikes: Int,

    @SerializedName("cheap")
    val cheap: Boolean,

    @SerializedName("dairyFree")
    val dairyFree: Boolean,

    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredients>,

    @SerializedName("glutenFree")
    val glutenFree: Boolean,

    @SerializedName("id")
    val id: Int,

    @SerializedName("image")
    val image: String,

    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,

    @SerializedName("sourceName")
    val sourceName: String,

    @SerializedName("sourceUrl")
    val sourceUrl: String,

    @SerializedName("sumary")
    val sumary: String,

    @SerializedName("title")
    val title: String,

)