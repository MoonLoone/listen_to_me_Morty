package com.example.listentomemorti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val viewModel = ListOfItemViewModel(application)
        CoroutineScope(Dispatchers.IO).launch {
            val viewModel = ListOfItemViewModel.getInstance(application)
            viewModel.makeData()
            startActivity(Intent(this@SplashScreenActivity, ListOfItemsActivity::class.java))
        }

    }
}