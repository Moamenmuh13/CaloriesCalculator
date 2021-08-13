package com.example.caloriecalculatorapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    var results: String? = null
    private val RESULTS_KEY = "RESULTS"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var resultsBMR: TextView = findViewById(R.id.results_bmr)
        results = intent.extras?.getDouble(RESULTS_KEY).toString()

        resultsBMR.text = "$results\nCalories/day"

    }

}