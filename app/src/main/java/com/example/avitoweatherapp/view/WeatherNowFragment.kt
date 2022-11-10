package com.example.avitoweatherapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.avitoweatherapp.databinding.FragmentWeatherNowBinding


class WeatherNowFragment : Fragment() {

    private lateinit var binding: FragmentWeatherNowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherNowBinding.inflate(inflater, container, false)
        refreshUi()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshUi()
    }

    @SuppressLint("SetTextI18n")
    fun refreshUi(){
        setFragmentResultListener("request_current") { _, bundle ->
            val currentData = bundle.getSerializable("request_current_key") as List<*>
            binding.textView1.text = currentData[2].toString()
            binding.textView2.text = currentData[0].toString() + "°"
            binding.textView3.text = currentData[1].toString()
        }
    }
}