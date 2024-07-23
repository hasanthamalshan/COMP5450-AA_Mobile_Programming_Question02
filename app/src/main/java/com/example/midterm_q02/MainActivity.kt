package com.example.midterm_q02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.midterm_q02.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tempInput = findViewById<EditText>(id.tempInput)
        val spinner = findViewById<Spinner>(id.spinner)
        val button = findViewById<Button>(id.button)
        val answerText = findViewById<TextView>(id.answer)

        val units = arrayOf("Celsius to Fahrenheit", "Fahrenheit to Celsius", "Celsius to Kelvin", "Kelvin to Celsius")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        button.setOnClickListener {
            val temp = tempInput.text.toString().toDoubleOrNull()
            val selectedUnit = spinner.selectedItem.toString()

            if (temp != null) {
                val result = when (selectedUnit) {
                    "Celsius to Fahrenheit" -> celsiusToFahrenheit(temp)
                    "Fahrenheit to Celsius" -> fahrenheitToCelsius(temp)
                    "Celsius to Kelvin" -> celsiusToKelvin(temp)
                    "Kelvin to Celsius" -> kelvinToCelsius(temp)
                    else -> "Invalid Conversion"
                }
                answerText.text = result.toString()
            } else {
                answerText.text = "Please enter a valid temperature"
            }
        }
    }

    private fun celsiusToFahrenheit(celsius: Double): Double {
        return (celsius * 9/5) + 32
    }

    private fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5/9
    }

    private fun celsiusToKelvin(celsius: Double): Double {
        return celsius + 273.15
    }

    private fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }
}