package com.example.avitoweatherapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avitoweatherapp.databinding.FragmentWeatherWeekBinding
import com.example.avitoweatherapp.view.adapters.WeatherWeekRVAdapter

class WeatherWeekFragment() : Fragment() {

    private val arrayListDate = ArrayList<String>()
    private val arrayListMaxTemp = ArrayList<String>()
    private val arrayListMinTemp = ArrayList<String>()
    private val arrayListImg = ArrayList<String>()

    var adapter = WeatherWeekRVAdapter(null, null,null,null,null)
    lateinit var binding: FragmentWeatherWeekBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherWeekBinding.inflate(inflater, container, false)

        setRecyclerView()

        refreshUi()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshUi()
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    fun refreshUi(){
        setFragmentResultListener("request_forecast_week") { _, bundle ->
            val forecastTodayData = bundle.getSerializable("request_forecast_week_key") as List<*>
            setArrayLists(forecastTodayData)
            binding.weatherWeekRecyclerView.adapter = WeatherWeekRVAdapter(
                requireActivity().baseContext,
                arrayListImg,
                arrayListMinTemp,
                arrayListMaxTemp,
                arrayListDate)
            binding.weatherWeekRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun setArrayLists(forecastTodayData: List<*>) {
        arrayListDate.clear()
        arrayListImg.clear()
        arrayListMaxTemp.clear()
        arrayListMinTemp.clear()
        for (i in 0..27 step 4){
            arrayListDate.add(forecastTodayData[i].toString())
            arrayListMinTemp.add(forecastTodayData[i + 1].toString())
            arrayListMaxTemp.add(forecastTodayData[i + 2].toString())
            arrayListImg.add(forecastTodayData[i + 3].toString())
        }
    }

    private fun setRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(activity?.baseContext,
            LinearLayoutManager.VERTICAL,
            false)
        binding.weatherWeekRecyclerView.layoutManager = linearLayoutManager
        binding.weatherWeekRecyclerView.adapter = adapter
    }

}