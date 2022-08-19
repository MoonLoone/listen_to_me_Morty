package com.example.listentomemorti

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.listentomemorti.adapters.ItemAdapter
import com.example.listentomemorti.pojo.ResultCharacterPojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ListOfItemsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_items)

            val rvItem = findViewById<RecyclerView>(R.id.rvItems)
            val adapter = ItemAdapter()
            val viewModel = ListOfItemViewModel.getInstance(application)


            adapter.onChrClickListener = object : ItemAdapter.OnCharacterClickListener {
                override fun onClick(character: ResultCharacterPojo) {
                        intent = CharacterDetailActivity.getIntentForStart(
                            this@ListOfItemsActivity,
                            character.id ?: 1
                        )
                        startActivity(intent)
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                    adapter.listOfCharacters = viewModel.getCharacters()
                    rvItem.adapter = adapter
                }

            findViewById<Button>(R.id.buttonFavourites).setOnClickListener {
                intent = Intent(this@ListOfItemsActivity, FavouriteCharacterActivity::class.java)
                startActivity(intent)
            }

        }


    }

