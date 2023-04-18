package com.example.booksearchingusingnaverapi.search

interface OnRecentKeywordClickListener {
    fun onKeywordClick(keyword: String)
    fun onKeywordDelete(recentSearch: RecentSearch)
}