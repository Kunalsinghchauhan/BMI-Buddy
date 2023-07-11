package com.example.bmibuddy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val imgMale = findViewById<ImageButton>(R.id.imgMale)
        val imgFemale = findViewById<ImageButton>(R.id.imgFemale)
        imgMale.setOnClickListener {
            imgMale.setBackgroundColor(Color.RED)
            imgFemale.setBackgroundColor(Color.WHITE)
        }
        imgFemale.setOnClickListener {
            imgMale.setBackgroundColor(Color.WHITE)
            imgFemale.setBackgroundColor(Color.RED)
        }

        btnCalculate.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (invalidInput(weight, height)) {
                var bmi =
                    weight.toDouble() / ((height.toDouble() / 100) * (height.toDouble() / 100))
                bmi = String.format("%.2f", bmi).toDouble()
                displayResult(bmi)
            }
        }
    }

    private fun invalidInput(weight: String?, height: String?): Boolean {
        when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Empty weight", Toast.LENGTH_SHORT).show()
                return false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Empty height", Toast.LENGTH_SHORT).show()
                return false
            }

            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Double) {
        val tvResult = findViewById<TextView>(R.id.tvResult)
        var tvDetails: TextView = findViewById(R.id.tvDetails)
        var color = 0
        tvResult.text = bmi.toString()
        when {
            bmi < 18.50 -> {
                tvDetails.text = "Underweight"
                color = R.color.resultUnderWeight
            }

            bmi in 18.50..24.99 -> {
                tvDetails.text = "Healthy"
                color = R.color.resultHealthy
            }

            bmi in 25.00..29.99 -> {
                tvDetails.text = "Over Weight"
                color = R.color.resultOverWeight
            }

            bmi > 29.99 -> {
                tvDetails.text = "Obese"
                color = R.color.resultObese
            }

        }
        tvDetails.setTextColor(ContextCompat.getColor(this, color))
    }
}