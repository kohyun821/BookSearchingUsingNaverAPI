package com.example.booksearchingusingnaverapi.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksearchingusingnaverapi.R
import com.example.booksearchingusingnaverapi.databinding.ItemSearchResultBinding

class SearchBooksAdapter(private val books: MutableList<BookItem>) :
    RecyclerView.Adapter<SearchBooksAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding: ItemSearchResultBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_search_result,
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.binding.bookItem = books[position]
        Glide.with(holder.itemView.context)
            .load(books[position].image)
            .into(holder.binding.ivThumb)
        holder.binding.executePendingBindings()
    }

    inner class BookViewHolder(val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateBooks(newBooks: List<BookItem>) {
        this.books.clear()
        this.books.addAll(newBooks)
        notifyDataSetChanged()
    }
}
