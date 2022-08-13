package com.example.listentomemorti.api


import com.example.listentomemorti.pojo.CharacterPojo
import com.example.listentomemorti.pojo.LocationPojo
import com.example.listentomemorti.pojo.ResultCharacterPojo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character/?")
    suspend fun getCharacter(@Query("page") page: Int): CharacterPojo


}