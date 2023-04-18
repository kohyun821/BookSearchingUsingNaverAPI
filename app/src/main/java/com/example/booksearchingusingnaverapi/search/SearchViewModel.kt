package com.example.booksearchingusingnaverapi.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    var keyword: MutableLiveData<String> = MutableLiveData("")
    val books = MutableLiveData<List<BookItem>>()
    var inputKeyword : MutableLiveData<Boolean> = MutableLiveData(false)

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
}