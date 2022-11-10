package com.example.avitoweatherapp.network

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GetLocation(private val context : Context, private val activity: Activity) {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var locationResponse = ""

    fun startLocate(callback: (String) -> Unit){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        checkLocationPermission(callback)
    }

    private fun checkLocationPermission(callback: (String) -> Unit){
        val task = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )

        }
        task.addOnSuccessListener {
            if(it!=null){
                locationResponse = "${it.latitude},${it.longitude}"
                CITY = "${it.latitude},${it.longitude}"
                callback.invoke(locationResponse)
                Toast.makeText(context, "${it.latitude}, ${it.longitude}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}