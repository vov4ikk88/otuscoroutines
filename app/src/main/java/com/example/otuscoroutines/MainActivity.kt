package com.example.otuscoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.otuscoroutines.data.CovidSummary
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var model: CovidViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init() {
        model = ViewModelProviders.of(this).get(CovidViewModel::class.java)
        var liveListData: LiveData<CovidSummary>? = model.getCovidSumLivedata()
        liveListData?.observe(this, object : Observer<CovidSummary> {
            override fun onChanged(t: CovidSummary?) {
                dateTextView.text = t?.Date.toString()
                newConfirmedTextView.text = t?.Global?.NewConfirmed.toString()
                newDeathsTextView.text = t?.Global?.NewDeaths.toString()
                newRecoveredTextView.text = t?.Global?.NewRecovered.toString()
                totalConfirmedTextView.text = t?.Global?.TotalConfirmed.toString()
                totalDeathsTextView.text = t?.Global?.TotalDeaths.toString()
                totalRecoveredTextView.text = t?.Global?.TotalRecovered.toString()
            }
        })
        refreshButton.setOnClickListener { loadLiveData() }
    }

    private fun loadLiveData() {
        model.regreshData()
    }
}
