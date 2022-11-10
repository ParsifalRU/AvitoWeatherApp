package com.example.avitoweatherapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.avitoweatherapp.R
import com.example.avitoweatherapp.databinding.FragmentMainBinding
import com.example.avitoweatherapp.network.CITY
import com.example.avitoweatherapp.network.GetLocation
import com.example.avitoweatherapp.network.WeatherApp
import com.example.avitoweatherapp.viewmodel.MainViewModel
import java.io.Serializable

class MainFragment : Fragment(){

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startFragments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getLocation()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setButton(){
        binding.Button.setOnClickListener{
            if (binding.appCompatEditText.text?.length != 0){
                CITY = binding.appCompatEditText.text.toString()
                val weatherListViewModel = ViewModelProvider(this)[MainViewModel::class.java]
                weatherListViewModel.fetchWeatherList((activity?.application as? WeatherApp)?.weatherApi)
                getLiveData(weatherListViewModel)
            }
        }
    }

    private fun  startFragments(){

        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.fragment_weather_now, WeatherNowFragment())
            ?.commit()

        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.fragment_weather_today, WeatherTodayFragment())
            ?.commit()

        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.fragment_weather_week, WeatherWeekFragment())
            ?.commit()

    }

   @SuppressLint("SetTextI18n")
    private fun getLiveData(weatherListViewModel: MainViewModel){
        weatherListViewModel.liveData.observe(viewLifecycleOwner) { data ->
            data?.let {

                val bundleCurrent = Bundle()
                val bundleForecastWeek = Bundle()
                val bundleForecastToday = Bundle()

                val currentData = listOf(
                    data.current.temp_c,
                    data.current.condition.text,
                    data.location.name
                ) as Serializable

                val forecastWeek = ArrayList<String>()
                for (i in 0..6 step 1) {
                    forecastWeek.add(data.forecast.forecastday[i].date)
                    forecastWeek.add(data.forecast.forecastday[i].day.maxtemp_c)
                    forecastWeek.add(data.forecast.forecastday[i].day.mintemp_c)
                    forecastWeek.add(data.forecast.forecastday[i].day.condition.icon)
                }

                val forecastToday = ArrayList<String>()
                for (i in 0..23 step 1) {
                    forecastToday.add(data.forecast.forecastday[0].hour[i].temp_c)
                    forecastToday.add(data.forecast.forecastday[0].hour[i].time)
                    forecastToday.add(data.forecast.forecastday[0].hour[i].condition.icon)
                }

                bundleCurrent.putSerializable("request_current_key", currentData)
                bundleForecastWeek.putSerializable("request_forecast_week_key", forecastWeek)
                bundleForecastToday.putSerializable("request_forecast_today_key", forecastToday)

                parentFragmentManager.setFragmentResult(
                    "request_current",
                    bundleCurrent
                )

                parentFragmentManager.setFragmentResult(
                    "request_forecast_week",
                    bundleForecastWeek
                )

                parentFragmentManager.setFragmentResult(
                    "request_forecast_today",
                    bundleForecastToday
                )
            }
        }

   }

    private fun getLocation(){
        GetLocation(requireContext(), requireActivity()).startLocate {
            val weatherListViewModel = ViewModelProvider(this)[MainViewModel::class.java]
            weatherListViewModel.fetchWeatherList((activity?.application as? WeatherApp)?.weatherApi)
            getLiveData(weatherListViewModel)
        }
    }
}