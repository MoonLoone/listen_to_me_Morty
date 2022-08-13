package com.example.listentomemorti.adapters

import android.opengl.Visibility
import android.os.Binder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listentomemorti.ListOfItemsActivity
import com.example.listentomemorti.databinding.ListItemBinding
import com.example.listentomemorti.pojo.CharacterPojo
import com.example.listentomemorti.pojo.LocationPojo
import com.example.listentomemorti.pojo.ResultCharacterPojo
import com.example.listentomemorti.pojo.ResultLocationPojo
import com.squareup.picasso.Picasso

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder>() {

    var listOfCharacters = listOf<ResultCharacterPojo>()

    inner class ItemAdapterViewHolder(val viewBinding: ListItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        val nameItm = viewBinding.tvName
        val typeItm = viewBinding.tvType
        val stateItm = viewBinding.tvState
        val speciesItm = viewBinding.tvSpecies
        val locationItm = viewBinding.tvLocation
        val picture = viewBinding.img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
        return ItemAdapterViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemAdapterViewHolder, position: Int) {
            val characterObj = listOfCharacters[position]
            with(holder) {
                with(characterObj) {
                    nameItm.text = name
                    typeItm.text = type
                    stateItm.text = status
                    speciesItm.text = species
                    locationItm.text = location?.name
                    Picasso.get().load(image).into(picture)
                }
            }


    }

    override fun getItemCount() =  listOfCharacters.size
}