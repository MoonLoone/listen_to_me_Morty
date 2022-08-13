package com.example.listentomemorti.database

import androidx.room.*
import com.example.listentomemorti.pojo.ResultCharacterPojo

@Dao
interface AppDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characters: List<ResultCharacterPojo>)

    @Query("SELECT * FROM character")
    fun getAllCharacters(): List<ResultCharacterPojo>

    @Query("SELECT * FROM character WHERE id == :id LIMIT 1")
    fun getInfoForCharacter(id: Int): ResultCharacterPojo

    @Query("SELECT * FROM character WHERE isFavourite == 1")
    fun getFavourites(): List<ResultCharacterPojo>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCharacter(characterPojo: ResultCharacterPojo)
}