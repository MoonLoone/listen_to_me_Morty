package com.example.listentomemorti

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.listentomemorti.api.ApiFactory
import com.example.listentomemorti.database.AppDatabase
import com.example.listentomemorti.pojo.ResultCharacterPojo

class ListOfItemViewModel(application: Application): AndroidViewModel(application){

   private val db = AppDatabase.getInstance(application)

   suspend fun makeData() {
           var pagesCount = ApiFactory.apiService.getCharacter(1).info?.pages
            val resultData = mutableListOf<ResultCharacterPojo>()
                if (pagesCount == null) pagesCount = 1
                    for (i in 1..pagesCount) {
                        val dataCharacters = ApiFactory.apiService.getCharacter(i).results
                        resultData.addAll(dataCharacters!!)
                    }
                    db.dbDAO().insertCharacters(resultData)
                }

    suspend fun getCharacters(): List<ResultCharacterPojo> {
        return db.dbDAO().getAllCharacters()
    }

    suspend fun getCharacter(id:Int): ResultCharacterPojo{
        return db.dbDAO().getInfoForCharacter(id)
    }

    suspend fun makeStartData() = ApiFactory.apiService.getCharacter(1).results


    fun updateCharacterInfo(characterPojo: ResultCharacterPojo) {
            db.dbDAO().updateCharacter(characterPojo)
    }

    suspend fun getFavourites(): List<ResultCharacterPojo>{
        return db.dbDAO().getFavourites()
    }
}