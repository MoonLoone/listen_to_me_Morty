package com.example.listentomemorti

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.listentomemorti.pojo.ResultCharacterPojo
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        if (!intent.hasExtra(POSITION_KEY)) {
            finish()
        }

        val positionOfCharacterInDB = intent.getIntExtra(POSITION_KEY, 1)
        val viewModel = ListOfItemViewModel(application)
        val favouriteImg = findViewById<ImageView>(R.id.ivFav)
        var character= ResultCharacterPojo()

        val job = CoroutineScope(Dispatchers.IO).launch {
            character = viewModel.getCharacter(positionOfCharacterInDB)
            favouriteImg.setOnClickListener {
                if (character.isFavourite) {
                    character.isFavourite = false
                    favouriteImg.setImageResource(R.drawable.unf)
                } else {
                    character.isFavourite = true
                    favouriteImg.setImageResource(R.drawable.fav)
                }
                CoroutineScope(Dispatchers.IO).launch{ viewModel.updateCharacterInfo(character) }
            }

        }

        CoroutineScope(Dispatchers.Main).launch {
            job.join()
            Picasso.get().load(character.image)
                .into(findViewById<ImageView>(R.id.ivCharacterDetail))
            if (character.isFavourite) favouriteImg.setImageResource(R.drawable.fav)
            findViewById<TextView>(R.id.tvNameDetail).text = character.name
            findViewById<TextView>(R.id.tvLocationDetail).text = character.location?.name
            findViewById<TextView>(R.id.tvOriginDetail).text = character.origin?.name
            findViewById<TextView>(R.id.tvSpeciesDetail).text = character.species
            findViewById<TextView>(R.id.tvStatusDetail).text = character.status
            findViewById<TextView>(R.id.tvEpisodesList).text = parseOfEpisode(character.episode)
        }
    }

    private fun parseOfEpisode(episodes: List<String>?): String{
        return if (episodes != null) {
            var result = ""
            for (item in episodes){
                val itemSub = item.substring(item.lastIndexOf('/')+1)
                result+= "$itemSub, "
            }
            result.substring(0,result.length-2)
        }else ""
    }


    companion object {
        private const val POSITION_KEY = "GET_POSITION"
        fun getIntentForStart(context: Context, position: Int): Intent {
            val intent = Intent(context, CharacterDetailActivity::class.java)
            intent.putExtra(POSITION_KEY, position)
            return intent
        }
    }
}