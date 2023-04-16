package com.example.booksearchingusingnaverapi.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    var keyword: MutableLiveData<String> = MutableLiveData("ㅁㄴㅇㄹ")

    var inputKeyword : MutableLiveData<Boolean> = MutableLiveData(false)

    fun searching(){
        println("버튼 클릭!")
        getBooks(keyword.value ?: "")
    }

    private fun getBooks(query: String) {
        val clientId = "****"
        val clientSecret = "****"

        RetrofitClient.naverAPI.searchBooks(clientId, clientSecret, query).enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                if (response.isSuccessful) {
                    val books = response.body()?.items ?: emptyList()
                    println("검색 결과: $books")
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