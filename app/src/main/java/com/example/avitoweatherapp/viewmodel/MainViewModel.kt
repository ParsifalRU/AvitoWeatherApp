package com.example.avitoweatherapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.avitoweatherapp.model.DataModel
import com.example.avitoweatherapp.network.GetLocation
import com.example.avitoweatherapp.network.WeatherApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers


class MainViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    val liveData = MutableLiveData<DataModel>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchWeatherList(weatherApi: WeatherApi?){
        weatherApi?.let {
        compositeDisposable.add(weatherApi.getWeatherWeek()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<DataModel>(){
                override fun onSuccess(t: DataModel) {
                    liveData.value = t
                    Log.d("LOGTAG", "onSuccess $t")
                }
                override fun onError(e: Throwable) {
                    Log.e("LOGTAG", "onError $e")
                }
            })
        )}
    }
}