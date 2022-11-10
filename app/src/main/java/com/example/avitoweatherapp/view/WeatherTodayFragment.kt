package com.example.avitoweatherapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avitoweatherapp.databinding.FragmentWeatherTodayBinding
import com.example.avitoweatherapp.view.adapters.WeatherTodayRVAdapter

class WeatherTodayFragment : Fragment() {

    private val arrayListTime = ArrayList<String>()
    private val arrayListTemp = ArrayList<String>()
    private val arrayListImg = ArrayList<String>()

    private val adapter = WeatherTodayRVAdapter(null, null, null, null)
    private lateinit var binding: FragmentWeatherTodayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherTodayBinding.inflate(inflater, container, false)

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
        setFragmentResultListener("request_forecast_today") { _, bundle ->
            val forecastTodayData = bundle.getSerializable("request_forecast_today_key") as List<*>
            setArrayLists(forecastTodayData)
            binding.weatherTodayRecyclerView.adapter = WeatherTodayRVAdapter(
                requireActivity().baseContext,
                arrayListImg,
                arrayListTemp,
                arrayListTime
            )
            binding.weatherTodayRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun setArrayLists(forecastTodayData: List<*>) {
        arrayListTemp.clear()
        arrayListTime.clear()
        arrayListImg.clear()
        for (i in 0..71 step 3){
            arrayListTime.add(forecastTodayData[i].toString())
            arrayListTemp.add(forecastTodayData[i + 1].toString())
            arrayListImg.add(forecastTodayData[i + 2].toString())
        }
    }

    private fun setRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(activity?.baseContext, LinearLayoutManager.HORIZONTAL, false)
        binding.weatherTodayRecyclerView.layoutManager = linearLayoutManager
        binding.weatherTodayRecyclerView.adapter = adapter
    }
}