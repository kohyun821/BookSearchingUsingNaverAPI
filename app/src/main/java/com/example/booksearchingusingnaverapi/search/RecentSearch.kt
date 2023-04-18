package com.example.booksearchingusingnaverapi.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_searches")
data class RecentSearch(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val keyword: String,
    val createdAt: Long = System.currentTimeMillis()
)
