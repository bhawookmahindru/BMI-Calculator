package com.example.bhawook54545434.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageboy = findViewById<ImageView>(R.id.boy_image)
        val imagegirl = findViewById<ImageView>(R.id.girl_image)
        val weight = findViewById<EditText>(R.id.weight_value)
        val height = findViewById<EditText>(R.id.height_value)
        val calbutton = findViewById<Button>(R.id.calculate_button)
        val bmi = findViewById<TextView>(R.id.bmi)
        val bmistatus = findViewById<TextView>(R.id.bmi_status)
        val bmiview = findViewById<LinearLayout>(R.id.bmi_view)
        val againcal = findViewById<TextView>(R.id.calculate_again)

        imageboy.setOnClickListener {
            imageboy.setImageResource(R.drawable.boy)
            imagegirl.setImageResource(R.drawable.blur_girl)
        }

        imagegirl.setOnClickListener {
            imagegirl.setImageResource(R.drawable.girl)
            imageboy.setImageResource(R.drawable.blur_boy)
        }

        calbutton.setOnClickListener {
            var weighValue = 0.0
            var heightValue = 0.0
            if (weight.text.toString().isNotEmpty()) {
                weighValue = weight.text.toString().toDouble()
            }
            if (height.text.toString().isNotEmpty()) {
                heightValue = (height.text.toString().toDouble() / 100)
            }

            if (weighValue > 0.0 && heightValue > 0.0) {
                val bmiValue = String.format("%.2f", weighValue / heightValue.pow(2))
                bmi.text = bmiValue
                bmistatus.text = bmiStatusValue(weighValue / heightValue.pow(2))
                bmiview.visibility = VISIBLE
                calbutton.visibility = GONE
            } else
                Toast.makeText(this, "Please input Weight and Height value greater than 0", Toast.LENGTH_LONG).show()  

        }
        againcal.setOnClickListener {
            bmiview.visibility = GONE
            calbutton.visibility = VISIBLE
            weight.text.clear()
            height.text.clear()
            weight.requestFocus()
        }

    }

    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if (bmi < 18.5)
            bmiStatus = "Under Weight"
        else if (bmi >= 18.5 && bmi < 25)
            bmiStatus = "Normal"
        else if (bmi >= 25 && bmi < 30)
            bmiStatus = "Overweight"
        else if (bmi > 30)
            bmiStatus = "Obese"
        return bmiStatus
    }
}
