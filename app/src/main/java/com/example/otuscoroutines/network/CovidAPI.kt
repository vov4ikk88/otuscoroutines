package com.example.otuscoroutines.network

import com.google.gson.JsonObject
import retrofit2.http.GET

interface CovidAPI {
    @GET("/summary")
    suspend fun getCovidSum(): JsonObject
}