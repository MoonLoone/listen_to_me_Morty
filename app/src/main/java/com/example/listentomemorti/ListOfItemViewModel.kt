package com.example.listentomemorti

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
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

    fun getCharacters(): List<ResultCharacterPojo> {
        return db.dbDAO().getAllCharacters()
    }

    fun getCharacter(id:Int): ResultCharacterPojo{
        return db.dbDAO().getInfoForCharacter(id)
    }


    fun updateCharacterInfo(characterPojo: ResultCharacterPojo) {
            db.dbDAO().updateCharacter(characterPojo)
    }

    fun getFavourites(): List<ResultCharacterPojo>{
        return db.dbDAO().getFavourites()
    }

    companion object{
        private val LOCK = Any()
        private var viewModel:ListOfItemViewModel? = null

        fun getInstance(application: Application): ListOfItemViewModel{
            synchronized(LOCK){
                viewModel?.let { return it }
                val instance = ListOfItemViewModel(application)
                viewModel = instance
                return instance
            }
        }
    }
}