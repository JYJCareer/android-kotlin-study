package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView ?=null
    private var tvAgeInMinute : TextView ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinute = findViewById(R.id.tvAgeInMinute)

        btnDatePicker.setOnClickListener {
        clickDatePicker()
        }


    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd =   DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                    _, selectedyear,selectedmonth,selecteddayOfMonth -> /*_ : view */
                Toast.makeText(this,
                    "Year was $selectedyear, month was ${selectedmonth+1},day of month was ${selecteddayOfMonth} ",Toast.LENGTH_LONG).show()

                val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.KOREA)
                val theDate = sdf.parse(selectedDate)
                theDate.let{ //theDate를 선택한 경우에만 실행된다.
                    val selectedDateInMinutes = theDate.time / 60000 //getTime() -> time

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let { //currentDate를 선택한 경우에만 실행된다.

                        val currentDateInMinutes =currentDate.time/60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMinute?.text = differenceInMinutes.toString()
                    }

                }

            },
            year,month,day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() -8640000
        dpd.show()





    }

}