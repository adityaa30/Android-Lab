package com.example.restaurentmenu


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox

class DishAdapter(
    private val dishes: ArrayList<Dish>,
    private val mOnSelectListener: OnSelectListener
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    companion object {
        private const val TAG = "DishAdapter"
    }

    val mSelectedDishes = HashSet<Dish>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_dish,
            parent,
            false
        ) as View
        return DishViewHolder(view)
    }

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(dishes[position])
    }

    public fun clearDishes() {
//        mSelectedDishes.clear()
//        notifyDataSetChanged()
    }



    inner class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val mContainer = view.findViewById(R.id.container) as MaterialCardView
        private val mCheckBox = view.findViewById(R.id.checkbox) as MaterialCheckBox
        private val mPrice = view.findViewById(R.id.price) as TextView

        init {
            mCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    mSelectedDishes.add(dishes[adapterPosition])
                    mOnSelectListener.onSelectDish(dishes[adapterPosition])
                    Log.v(TAG, "Added: ${dishes[adapterPosition]}")
                } else {
                    mSelectedDishes.remove(dishes[adapterPosition])
                    mOnSelectListener.onDeselectDish(dishes[adapterPosition])
                    Log.v(TAG, "Removed: ${dishes[adapterPosition]}")
                }
            }
        }

        fun bind(currentDish: Dish) {
            mCheckBox.text = currentDish.name
            mCheckBox.isChecked = mSelectedDishes.contains(currentDish)
            mPrice.text = currentDish.price.toString()
        }
    }
}