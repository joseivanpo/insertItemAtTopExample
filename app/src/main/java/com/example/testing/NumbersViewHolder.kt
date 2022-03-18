package com.example.testing

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumbersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView = itemView.findViewById(R.id.textViewNumber) as TextView

    fun bind(num: Numbers) {
        textView.text = num.number.toString()
    }
}