package com.example.listentomemorti

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.listentomemorti.adapters.ItemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ListOfItemsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_items)

        val rvItem = findViewById<RecyclerView>(R.id.rvItems)
        val adapter = ItemAdapter()
        val viewModel = ListOfItemViewModel(application)

        CoroutineScope(Dispatchers.Main).launch{
            adapter.listOfCharacters = viewModel.makeStartData()?: listOf()
            rvItem.adapter = adapter
        }

        CoroutineScope(Dispatchers.IO).launch {
                viewModel.makeData()
                adapter.listOfCharacters = viewModel.getCharacters()
                }
            }
    }
