package com.example.listentomemorti.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.listentomemorti.pojo.LocationPojo
import com.example.listentomemorti.pojo.ResultCharacterPojo
import com.example.listentomemorti.pojo.ResultLocationPojo

@Dao
interface AppDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<ResultCharacterPojo>)

    @Query("SELECT * FROM character")
    fun getAllCharacters(): List<ResultCharacterPojo>


}