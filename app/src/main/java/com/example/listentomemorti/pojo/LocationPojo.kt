package com.example.listentomemorti.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class LocationPojo (
    @SerializedName("results")
    @Expose
    val results:List<ResultLocationPojo>? = null
)