package com.example.booksearchingusingnaverapi.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.booksearchingusingnaverapi.search.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(application: Application) : AndroidViewModel(application){
    var keyword: MutableLiveData<String> = MutableLiveData("")
    val books = MutableLiveData<List<BookItem>>()
    var inputKeyword : MutableLiveData<Boolean> = MutableLiveData(false)
    private val recentSearchDao = AppDatabase.getDatabase(application).recentSearchDao()
    val recentSearches: LiveData<List<RecentSearch>> = recentSearchDao.getAll()


    fun searching2(){
        println("searching2 입력값${keyword.value}입니다.")
        getBooks(keyword.value?:"")
    }
    fun searching(){
        println("searching 입력값${keyword.value}입니다.")
        getBooks(keyword.value?:"")
    }

    private fun getBooks(query: String) {
        val clientId = "CEe4IT2zVqDLPPEIdaye"
        val clientSecret = "u0xA7otC7o"

        addRecentSearch(query) // 최근 검색어 추가
        RetrofitClient.naverAPI.searchBooks(clientId, clientSecret, query).enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                if (response.isSuccessful) {
                    val booksResponse = response.body()?.items ?: emptyList()
                    books.value = booksResponse
                    println("booksResponse 검색 결과: $booksResponse")
                    println("books.value검색 결과: ${books.value}")
                } else {
                    println("검색 실패: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                println("API 요청 실패: $t")
            }
        })
    }

    // 검색어 추가
    private fun addRecentSearch(keyword: String) {
        println("addRecentSearch 실행!")
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchDao.insert(RecentSearch(keyword = keyword))
        }
    }

    // 검색어 삭제
    fun deleteRecentSearch(recentSearch: RecentSearch) {
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchDao.delete(recentSearch)
        }
    }
}