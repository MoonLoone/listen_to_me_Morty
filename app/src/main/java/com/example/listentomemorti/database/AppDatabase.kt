package com.example.listentomemorti.database

import android.content.Context
import androidx.room.*
import com.example.listentomemorti.converters.CharacterConverter
import com.example.listentomemorti.pojo.CharacterPojo
import com.example.listentomemorti.pojo.LocationPojo
import com.example.listentomemorti.pojo.ResultCharacterPojo

@Database(entities = [ResultCharacterPojo::class], version = 2, exportSchema = false)
@TypeConverters(CharacterConverter::class)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private var database: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()


        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                database?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                database = instance
                return instance
            }
        }
    }

    abstract fun dbDAO(): AppDatabaseDAO
}