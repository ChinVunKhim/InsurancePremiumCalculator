package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    lateinit var  myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalc = findViewById<Button>(R.id.buttonCalculate)
        val btnReset = findViewById<Button>(R.id.buttonReset)
        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)

        btnCalc.setOnClickListener {
            myData.result = "RM%.2f".format(getPremium())
            display()
        }

        btnReset.setOnClickListener {
            ageSpinner.selectedItemPosition
            ageSpinner.setSelection(0)
            radioGroupGender.clearCheck()
            smokerCheckBox.setChecked(false)
            totalPremiumOutput.setText("---")
        }
        display()
    }

    fun display()
    {
        totalPremiumOutput.text = myData.result
    }

    fun getPremium():Double
    {
        return when(ageSpinner.selectedItemPosition)
        {
            0-> 60.0
            1-> 70.0 + (if(radioButtonMale.isChecked) 50.00 else 0.0) + (if(smokerCheckBox.isChecked) 100.00 else 0.0)
            2-> 90.0 + (if(radioButtonMale.isChecked) 100.00 else 0.0) + (if(smokerCheckBox.isChecked) 150.00 else 0.0)
            3-> 120.0 + (if(radioButtonMale.isChecked) 150.00 else 0.0) + (if(smokerCheckBox.isChecked) 200.00 else 0.0)
            4-> 150.0 + (if(radioButtonMale.isChecked) 200.00 else 0.0) + (if(smokerCheckBox.isChecked) 250.00 else 0.0)
            else-> 150.0 + (if(radioButtonMale.isChecked) 200.00 else 0.0) + (if(smokerCheckBox.isChecked) 300.00 else 0.0)
        }
    }


}
