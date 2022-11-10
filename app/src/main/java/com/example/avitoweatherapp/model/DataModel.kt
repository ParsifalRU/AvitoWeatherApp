package com.example.avitoweatherapp.model

data class DataModel(
    val location: DataLocation,
    val current: Current,
    val forecast: DataForecast
)

data class DataLocation(
    val name: String,
    val country : String,
    val tz_id : String,
)

data class Current(
    val temp_c: String,
    val is_day: String,
    val condition: DataCondition
)

data class DataForecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val day: DataDay,
    val date: String,
    val hour: List<HourData>
)

data class DataDay(
    val maxtemp_c: String,
    val mintemp_c: String,
    val condition: DataCondition
)

data class DataCondition(
    val icon: String,
    val text: String
)

data class HourData(
    val temp_c: String,
    val time: String,
    val condition: DataCondition
)