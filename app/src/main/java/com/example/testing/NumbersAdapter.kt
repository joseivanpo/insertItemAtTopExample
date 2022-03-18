package com.example.testing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class NumbersAdapter : PagingDataAdapter<Numbers, NumbersViewHolder>(diffUtils) {

    companion object {
        val diffUtils = object : DiffUtil.ItemCallback<Numbers>() {
            override fun areItemsTheSame(oldItem: Numbers, newItem: Numbers): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Numbers, newItem: Numbers): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NumbersViewHolder(layoutInflater.inflate(R.layout.item_number, parent, false))
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}