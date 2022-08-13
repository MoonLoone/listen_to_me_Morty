package com.example.listentomemorti.converters

import androidx.room.TypeConverter
import com.example.listentomemorti.pojo.LocationPojo
import com.example.listentomemorti.pojo.ResultCharacterPojo
import com.example.listentomemorti.pojo.ResultLocationPojo

class CharacterConverter {

    @TypeConverter
    fun locationToString(value: ResultLocationPojo): String{
        return value.name.toString()
    }

    @TypeConverter
    fun stringToLocation(value: String): ResultLocationPojo{
        return ResultLocationPojo(name = value)
    }

    @TypeConverter
    fun characterToString(value: ResultCharacterPojo): String{
        return value.name.toString()
    }

    @TypeConverter
    fun stringToCharacter(value: String): ResultCharacterPojo{
        return ResultCharacterPojo(name = value)
    }

    @TypeConverter
    fun listToString(value: List<String>): String{
        return value.joinToString()
    }

    @TypeConverter
    fun stringToList(value: String): List<String>{
        return value.split(",").map { it.trim() }
    }

}