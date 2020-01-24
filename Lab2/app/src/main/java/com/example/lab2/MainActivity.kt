package com.example.lab2

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var mNameText: EditText
    lateinit var mDateButton: MaterialButton
    lateinit var mGender: Spinner
    lateinit var mDeptRadioGroup: RadioGroup
    lateinit var mRating: RatingBar
    lateinit var mSubmitButton: MaterialButton


    lateinit var mResults: TextView


    val data = Data(0.0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mResults = findViewById(R.id.results)

        mNameText = findViewById(R.id.name)
        mDateButton = findViewById(R.id.dob)
        mDateButton.setOnClickListener { showDatePicker() }

        mGender = findViewById(R.id.gender)
        val categories = ArrayList<String>();
        categories.add("Male")
        categories.add("Female")
        val dataAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            categories
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        mGender.apply {
            adapter = dataAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) = Unit

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    data.gender = categories.get(position)
                    showToast("Gender: ${data.gender}")
                }
            }
        }


        mDeptRadioGroup = findViewById(R.id.department)
        mDeptRadioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(view: RadioGroup?, resId: Int) {
                val radio = findViewById(resId) as RadioButton
                data.dept = radio.text.toString()
            }
        })
        mRating = findViewById(R.id.rating)
        mSubmitButton = findViewById(R.id.submit)
        mSubmitButton.setOnClickListener {
            data.name = mNameText.text.toString()
            data.skill = mRating.rating
            showTheResult()

        }
    }

    fun showDatePicker() {
        // Get Current Date
        // Get Current Date
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        val onDateSetListener = object : OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, date: Int) {
                data.dob = "${date}/${month + 1}/${year}"
                mDateButton.text = data.dob
                showToast("Date is: ${data.dob}")
            }
        }

        val datePickerDialog = DatePickerDialog(
            this,
            onDateSetListener,
            mYear,
            mMonth,
            mDay
        )
        datePickerDialog.show()
    }

    fun showToast(value: String) {
        Toast.makeText(applicationContext, value, Toast.LENGTH_LONG).show()
    }

    fun showTheResult() {
        val ans = "Name:\t${data.name}\n" +
                "Date of Birth:\t${data.dob}\n" +
                "Gender:\t${data.gender}\n" +
                "Department:\t${data.dept}\n" +
                "Skill:\t${data.skill}\n"

        mResults.apply {
            text = ans
            visibility = View.VISIBLE
        }

    }
}
