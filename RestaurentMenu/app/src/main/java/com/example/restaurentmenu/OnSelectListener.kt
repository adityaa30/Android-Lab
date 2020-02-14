package com.example.restaurentmenu

interface OnSelectListener {
    fun onSelectDish(dish: Dish)
    fun onDeselectDish(dish: Dish)
}