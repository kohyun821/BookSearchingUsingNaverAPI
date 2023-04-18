package com.example.booksearchingusingnaverapi.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearchingusingnaverapi.R
import com.example.booksearchingusingnaverapi.databinding.ItemResentKeyworkBinding

class RecentKeywordAdapter : RecyclerView.Adapter<RecentKeywordAdapter.ViewHolder>() {
    private val items = mutableListOf<RecentSearch>()

    fun setData(data: List<RecentSearch>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemResentKeyworkBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_resent_keywork,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.recentKeyword = items[position]
    }

    class ViewHolder(val binding: ItemResentKeyworkBinding) : RecyclerView.ViewHolder(binding.root)
}
