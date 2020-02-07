package com.example.restaurentmenu


import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox
import org.w3c.dom.Text

class DishAdapter(
    private val dishes: ArrayList<Dish>
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    companion object {
        private const val TAG = "DishAdapter"
    }

    val mSelectedDisheh = HashSet<Dish>()


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

    inner class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val mContainer = view.findViewById(R.id.container) as MaterialCardView
        private val mCheckBox = view.findViewById(R.id.checkbox) as MaterialCheckBox
        private val mPrice = view.findViewById(R.id.name) as TextView

        init {
            mCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked) {
                    mSelectedDisheh.add(dishes[adapterPosition])
                } else {
                    mSelectedDisheh.remove(dishes[adapterPosition])
                }
            }
        }

        fun bind(currentDist: Dish) {

            mCheckBox.text = currentDist.name

        }
    }
}