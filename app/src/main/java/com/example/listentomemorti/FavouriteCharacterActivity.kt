package com.example.listentomemorti

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.listentomemorti.adapters.ItemAdapter
import com.example.listentomemorti.pojo.ResultCharacterPojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_character)
        val viewModel = ListOfItemViewModel.getInstance(application)
        val rvFavourites = findViewById<RecyclerView>(R.id.rvFavourites)
        val adapterFavourites = ItemAdapter()

        adapterFavourites.onChrClickListener = object : ItemAdapter.OnCharacterClickListener {
            override fun onClick(character: ResultCharacterPojo) {
                intent = CharacterDetailActivity.getIntentForStart(
                    this@FavouriteCharacterActivity,
                    character.id ?: 1
                )
                startActivity(intent)
            }
        }

        val job = CoroutineScope(Dispatchers.IO).launch {
            val a = viewModel.getFavourites()
            adapterFavourites.listOfCharacters = a
        }

        CoroutineScope(Dispatchers.Main).launch {
            job.join()
            rvFavourites.adapter = adapterFavourites
        }
    }


}