package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var tvSelectedDate:TextView
    lateinit var datepickerBtn:Button
    lateinit var tvAgeInMinute:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datepickerBtn = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        tvAgeInMinute = findViewById<TextView>(R.id.tvAgeInMinute)

        datepickerBtn.setOnClickListener { view->
            clickdatepicker(view)
            Toast.makeText(this,"button worked",Toast.LENGTH_SHORT).show()
        }
    }

    fun clickdatepicker(view: View){
        // get date from calendar
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
            val selectedDate = "$day/${month+1}/$year"
            tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val dateInMinute = theDate!!.time/ (1000*60)
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinute = currentDate!!.time / (1000*60)
            val diffInMinute = currentDateInMinute - dateInMinute

            tvAgeInMinute.setText(diffInMinute.toString())


        }, year, month, day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}