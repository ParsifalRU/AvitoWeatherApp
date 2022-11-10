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
import com.example.avitoweatherapp.model.DataModel

class WeatherWeekRVAdapter(
    private val context: Context?,
    private val arrayListImage: ArrayList<String>?,
    private val arrayListMaxTemp: ArrayList<String>?,
    private val arrayListMinTemp: ArrayList<String>?,
    private val arrayListData: ArrayList<String>?

    ) : RecyclerView.Adapter<WeatherWeekRVAdapter.WeatherWeekViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WeatherWeekViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.weather_week_item, viewGroup, false)

        return WeatherWeekViewHolder(view)
    }

    class WeatherWeekViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById<ImageView>(R.id.imageView)
        val textViewDayOfWeek: TextView = view.findViewById<TextView>(R.id.textView4)
        val textViewMinTemp: TextView = view.findViewById<TextView>(R.id.textView5)
        val textViewMaxTemp: TextView = view.findViewById<TextView>(R.id.textView6)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherWeekViewHolder, position: Int) {
        holder.textViewDayOfWeek.text = arrayListData?.get(position).toString()
        holder.textViewMaxTemp.text = arrayListMaxTemp?.get(position).toString() + "°"
        holder.textViewMinTemp.text = arrayListMinTemp?.get(position).toString() + "°"
        if (context != null){
            downloadImage(context, holder.imageView, "https:${arrayListImage?.get(position)}")
        }
    }

    override fun getItemCount(): Int {
        return 7
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
