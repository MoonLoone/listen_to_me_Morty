package com.example.listentomemorti.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class InfoPojo (

    @SerializedName("count")
    @Expose
    val count: Int? = null,

    @SerializedName("pages")
    @Expose
    val pages: Int? = null

)