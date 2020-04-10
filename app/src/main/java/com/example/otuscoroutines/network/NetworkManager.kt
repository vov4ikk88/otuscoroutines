package com.example.otuscoroutines.network

import android.util.Log
import com.example.otuscoroutines.data.CovidSummary
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    init {
        createRetrofit()
    }

    val TAG = "NetworkManager"
    lateinit var apiService: CovidAPI

    private fun createRetrofit() {
        Log.d(TAG, "createRetrofit")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.covid19api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(CovidAPI::class.java)
    }

    suspend fun getCovidSummary(): CovidSummary {
        val covidSummary = apiService.getCovidSum()
        val d: CovidSummary = Gson().fromJson(covidSummary, CovidSummary::class.java)
        return d
    }
}