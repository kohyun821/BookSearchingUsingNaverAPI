package com.example.booksearchingusingnaverapi.search

data class BooksResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<BookItem>
)

data class BookItem(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val price: Int,
    val discount: Int,
    val publisher: String,
    val isbn: String,
    val description: String,
    val pubdate: String
)