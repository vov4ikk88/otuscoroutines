package com.example.otuscoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otuscoroutines.data.CovidSummary


class CovidViewModel : ViewModel() {
     var covidSumLivedata: MutableLiveData<CovidSummary>? = null


    fun getCovidSumLivedata(): LiveData<CovidSummary>?{
        if (covidSumLivedata == null){
            regreshData()
        }
        return covidSumLivedata
    }

    public fun regreshData() {

    }
}