package com.example.booksearchingusingnaverapi.search.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booksearchingusingnaverapi.search.RecentSearch

@Dao
interface RecentSearchDao {
    @Query("SELECT * FROM recent_searches ORDER BY createdAt DESC limit 10")
    fun getAll(): LiveData<List<RecentSearch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recentSearch: RecentSearch)

    @Delete
    fun delete(recentSearch: RecentSearch)

    @Query("DELETE FROM recent_searches WHERE keyword = :keyword")
    fun deleteByKeyword(keyword: String)
}