package com.example.fetchrewards.features.displayitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.R
import com.example.fetchrewards.domain.model.Item

class ParentListAdapter : ListAdapter<Pair<Int, List<Item>>, ParentListAdapter.ParentViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_parent_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val listId: TextView
        private val childRecyclerView: RecyclerView

        init {
            listId = itemView.findViewById(R.id.list_id)
            childRecyclerView = itemView.findViewById(R.id.child_recycler_view)
        }

        fun bind(pair: Pair<Int, List<Item>>) {
            listId.text = "${pair.first}"
            val childListAdapter = ChildListAdapter()
            childListAdapter.submitList(pair.second)
            childRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(itemView.context, 4)
                adapter = childListAdapter
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Pair<Int, List<Item>>>() {
        override fun areItemsTheSame(oldItem: Pair<Int, List<Item>>, newItem: Pair<Int, List<Item>>) =
            oldItem.first == newItem.first

        override fun areContentsTheSame(oldItem: Pair<Int, List<Item>>, newItem: Pair<Int, List<Item>>) =
            oldItem == newItem

    }
}