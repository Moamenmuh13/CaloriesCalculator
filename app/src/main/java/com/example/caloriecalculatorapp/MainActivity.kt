package com.example.caloriecalculatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val WEIGHT_BMR: Double = 10.0
    private val HEIGHT_BMR: Double = 6.25
    private val AGE_BMR: Double = 5.0
    private val MALE_BMR: Double = 5.0
    private val FEMALE_BMR: Double = 161.0
    private var RESULTS: Double? = 0.0

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    lateinit var calculateBtn: Button
    lateinit var weightNum: EditText
    lateinit var heightNum: EditText
    lateinit var ageNum: EditText

    private var weightKG: Double? = null
    private var heightCM: Double? = null
    private var age: Double? = null

    private val TAG = "MainActivity"
    private val RESULTS_KEY = "RESULTS"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Calories Calculator"

        radioGroup = findViewById(R.id.radioGroup)
        calculateBtn = findViewById(R.id.calculateBtn)

        weightNum = findViewById(R.id.weight_KG)
        heightNum = findViewById(R.id.height_CM)
        ageNum = findViewById(R.id.age)

        calculateBtn.setOnClickListener {
            sendResultToActivity()
        }
    }

    private fun sendResultToActivity() {
        val selectedId: Int? = radioGroup?.checkedRadioButtonId
        radioButton = findViewById(selectedId!!)

        when (selectedId) {
            R.id.male -> caloriesCalculateForMale()
            R.id.female -> caloriesCalculateForFemale()
        }
    }

    private fun setValues() {
        weightKG = weightNum.text.toString().toDoubleOrNull()
        heightCM = heightNum.text.toString().toDoubleOrNull()
        age = ageNum.text.toString().toDoubleOrNull()
    }


    private fun caloriesCalculateForMale() {
        setValues()
        if (isDataEmpty()) {
            if (isDataValid()) {
                caloriesResults(MALE_BMR)
                startingActivity()
            } else {
                Toast.makeText(this, "Try entering data", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun caloriesCalculateForFemale() {
        setValues()
        if (isDataEmpty()) {
            if (isDataValid()) {
                caloriesResults(FEMALE_BMR)
                startingActivity()
            }
        } else {
            Toast.makeText(this, "Try entering data", Toast.LENGTH_SHORT).show()
        }
    }


    private fun caloriesResults(genderBMR: Double): Double? {
        RESULTS =
            (WEIGHT_BMR * weightKG!!) + (HEIGHT_BMR * heightCM!!) - (AGE_BMR * age!!) - (genderBMR)
        return RESULTS
    }


    private fun startingActivity() {
        startActivity(
            Intent(this, ResultActivity::class.java)
                .putExtra(RESULTS_KEY, RESULTS)
        )
    }

    private fun isDataEmpty(): Boolean {
        val isDataCorrect = true
        when {
            weightNum.text.isBlank() -> {
                weightNum.error = "Add Weight Number"
                !isDataCorrect
            }
            heightNum.text.isBlank() -> {
                weightNum.error = "Add Height Number"
                !isDataCorrect
            }
            ageNum.text.isBlank() -> {
                ageNum.error = "Add Age Number"
                !isDataCorrect
            }
            else -> return isDataCorrect
        }
        return !isDataCorrect
    }

    private fun isDataValid(): Boolean {
        val isDataCorrect = true
        when {
            weightKG!! !in (40.0..120.0) -> {
                !isDataCorrect
            }
            heightCM!! !in (145.0..200.0) -> {
                !isDataCorrect
            }
            age!! !in (16.0..60.0) -> {
                !isDataCorrect
            }
            else -> return isDataCorrect
        }

        return !isDataCorrect
    }
}