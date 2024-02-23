package com.example.fetchrewards.features.displayitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.R
import com.example.fetchrewards.domain.model.Item

class ChildListAdapter : ListAdapter<Item, ChildListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_child_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView

        init {
            name = itemView.findViewById(R.id.name)
        }

        fun bind(item: Item) {
            name.text = item.name
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Item, newItem: Item) =
            oldItem == newItem

    }
}