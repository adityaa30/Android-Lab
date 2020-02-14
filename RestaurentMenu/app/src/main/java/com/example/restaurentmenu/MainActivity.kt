package com.example.restaurentmenu

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var mCuisinesList: RecyclerView
    private lateinit var mCuisinesAdapter: CuisineAdapter
    private lateinit var mCuisines: ArrayList<Cuisine>

    private lateinit var mBillButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mCuisines = getCuisines()
        mCuisinesAdapter = CuisineAdapter(mCuisines)

        mCuisinesList = findViewById(R.id.cuisines)
        mCuisinesList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mCuisinesAdapter
        }

        mBillButton = findViewById(R.id.billButton)
        mBillButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage(mCuisinesAdapter.getBill())
                .setCancelable(false)
                .setNegativeButton("Add more dishes?") { dialog, id -> dialog.cancel() }
                .setPositiveButton("Order") { dialog, id ->
                    Toast.makeText(
                        applicationContext,
                        "Your order has been placed successfully!",
                        Toast.LENGTH_LONG
                    ).show()
                    dialog.cancel()
                }

            val alert = dialogBuilder.create()
            alert.setTitle("Final Bill")
            alert.show()
        }
    }

    private fun getCuisines(): ArrayList<Cuisine> {
        return arrayListOf(
            Cuisine(
                "American", arrayListOf(
                    Dish("Buffalo chicken", 300),
                    Dish("Texas BBQ medley", 200),
                    Dish("S'mores dip", 80)
                )
            ),
            Cuisine(
                "British", arrayListOf(
                    Dish("Cherry & almond tarts", 150),
                    Dish("No-fail Yorkies", 200),
                    Dish("St Clement’s pie", 80)
                )
            ),
            Cuisine(
                "Caribbean", arrayListOf(
                    Dish("Rum punch", 120),
                    Dish("Jerk chicken with rice & peas", 200),
                    Dish("Goat curry", 160)
                )
            )
        )
    }

}
