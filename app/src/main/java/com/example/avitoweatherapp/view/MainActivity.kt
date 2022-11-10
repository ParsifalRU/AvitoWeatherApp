package com.example.avitoweatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.avitoweatherapp.R
import com.example.avitoweatherapp.network.GetLocation
import com.example.avitoweatherapp.network.WeatherApp
import com.example.avitoweatherapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_main, MainFragment())
            .commit()
    }
}