package com.example.listentomemorti.pojo

import androidx.room.Entity
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class CharacterPojo (

    @SerializedName("info")
    @Expose
    val info:InfoPojo? = null,

    @SerializedName("results")
    @Expose
    val results: List<ResultCharacterPojo>? = null
)