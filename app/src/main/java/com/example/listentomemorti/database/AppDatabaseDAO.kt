package com.example.listentomemorti.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.listentomemorti.pojo.LocationPojo
import com.example.listentomemorti.pojo.ResultCharacterPojo
import com.example.listentomemorti.pojo.ResultLocationPojo

@Dao
interface AppDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characters: List<ResultCharacterPojo>)

    @Query("SELECT * FROM character")
    fun getAllCharacters(): List<ResultCharacterPojo>

    @Query("SELECT * FROM character WHERE id == :id LIMIT 1")
    fun getInfoForCharacter(id: Int): ResultCharacterPojo

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCharacter(characterPojo: ResultCharacterPojo)
}