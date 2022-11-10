package com.example.avitoweatherapp.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.avitoweatherapp.R

class WeatherTodayRVAdapter(
    private val context: Context?,
    private val arrayListImage: ArrayList<String>?,
    private val arrayListTemp: ArrayList<String>?,
    private val arrayListTime: ArrayList<String>?
    ): RecyclerView.Adapter<WeatherTodayRVAdapter.WeatherTodayViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WeatherTodayViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.weather_today_item, viewGroup, false)
        return WeatherTodayViewHolder(view)
    }

    class WeatherTodayViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewDay = view.findViewById<TextView>(R.id.textView3)!!
        val textViewTemp = view.findViewById<TextView>(R.id.textView)!!
        val imageViewImg = view.findViewById<ImageView>(R.id.imageView2)!!
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherTodayViewHolder, position: Int) {
        if (context != null){
        holder.textViewDay.text = arrayListTemp?.get(position).toString().drop(10).dropLast(3)
        holder.textViewTemp.text = arrayListTime?.get(position).toString() + "°"
        downloadImage(context, holder.imageViewImg, "https:${arrayListImage?.get(position)}")
        }
    }

    override fun getItemCount(): Int {
        return 24
    }

}
private fun downloadImage(context: Context, imageView:ImageView, url: String){
    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.renew)
        .into(imageView)
}
