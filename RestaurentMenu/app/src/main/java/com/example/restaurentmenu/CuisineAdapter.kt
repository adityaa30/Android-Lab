package com.example.restaurentmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class CuisineAdapter (
    private val cuisines: ArrayList<Cuisine>
) : RecyclerView.Adapter<CuisineAdapter.CuisineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_cuisines,
            parent,
            false
        ) as View
        return CuisineViewHolder(view)
    }

    val mSelectedDishes = HashSet<Dish>()

    override fun getItemCount(): Int = cuisines.size

    override fun onBindViewHolder(holder: CuisineViewHolder, position: Int) {
        holder.bind(cuisines[position])
    }

    public fun getBill(): String {
        var bill = "";
        var totalPrice = 0
        for (dish in mSelectedDishes) {
            bill += "${dish.name}\t${dish.price}\n"
            totalPrice += dish.price
        }
        bill += "Total Amount\t${totalPrice}"
        return bill
    }

    inner class CuisineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mContainer = view.findViewById(R.id.container) as MaterialCardView
        private val mDishList = view.findViewById(R.id.dishes) as RecyclerView
        private val mName = view.findViewById(R.id.name) as TextView

        private val mDishes = ArrayList<Dish>()
        private val mDishAdapter: DishAdapter

        init {
            // Init RecyclerView
            mDishAdapter = DishAdapter(mDishes, object : OnSelectListener {
                override fun onSelectDish(dish: Dish) {
                    mSelectedDishes.add(dish)
                }

                override fun onDeselectDish(dish: Dish) {
                    mSelectedDishes.remove(dish)
                }
            })
            mDishList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mDishAdapter
            }


            mName.setOnClickListener {
                mDishAdapter.clearDishes()
                if(mDishList.visibility == View.GONE) {
                    mDishList.visibility = View.VISIBLE
                } else {
                    mDishList.visibility = View.GONE
                }
            }
        }

        fun bind(currentCuisine: Cuisine) {
            mDishes.clear()
            mDishes.addAll(currentCuisine.dishes)
            mDishAdapter.notifyDataSetChanged()
            mName.text = currentCuisine.name
        }
    }
}