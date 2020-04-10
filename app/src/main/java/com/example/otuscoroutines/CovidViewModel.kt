package com.example.otuscoroutines

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.otuscoroutines.data.CovidSummary
import com.example.otuscoroutines.network.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CovidViewModel : ViewModel() {
     var covidSumLivedata: MutableLiveData<CovidSummary>? = null


    fun getCovidSumLivedata(): LiveData<CovidSummary>?{
        if (covidSumLivedata == null){
            covidSumLivedata = MutableLiveData()
            refreshData()
        }
        return covidSumLivedata
    }

    fun refreshData() {
        viewModelScope.launch {
            var covidSum: CovidSummary? = null
            withContext(Dispatchers.IO){
                covidSum = NetworkManager.getCovidSummary()
            }
            covidSumLivedata?.postValue(covidSum)
        }
    }

}