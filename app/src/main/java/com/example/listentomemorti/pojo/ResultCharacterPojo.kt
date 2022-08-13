package com.example.listentomemorti.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.listentomemorti.converters.CharacterConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "character")
data class ResultCharacterPojo (

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("species")
    @Expose
    val species: String? = null,

    @SerializedName("type")
    @Expose
    val type: String? = null,

    @SerializedName("location")
    @Expose
    val location: ResultLocationPojo? = null,

    @SerializedName("image")
    @Expose
    val image: String? = null,

)