package com.example.booksearchingusingnaverapi.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearchingusingnaverapi.R
import com.example.booksearchingusingnaverapi.databinding.ItemResentKeyworkBinding
import com.example.booksearchingusingnaverapi.search.OnRecentKeywordClickListener

class RecentKeywordAdapter(private val listener: OnRecentKeywordClickListener) : RecyclerView.Adapter<RecentKeywordAdapter.ViewHolder>()  {
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
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.recentKeyword = items[position]
    }

    class ViewHolder(val binding: ItemResentKeyworkBinding, private val listener: OnRecentKeywordClickListener) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onKeywordClick(binding.recentKeyword?.keyword ?: "")
            }
            binding.ivKeywordDelete.setOnClickListener {
                binding.recentKeyword?.let { recentSearch ->
                    listener.onKeywordDelete(recentSearch)
                }
            }
        }
    }

}
