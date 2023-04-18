package com.example.booksearchingusingnaverapi.search

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearchingusingnaverapi.R
import com.example.booksearchingusingnaverapi.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var booksAdapter: SearchBooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.viewModel = searchViewModel
        binding.lifecycleOwner=this
        binding.activity=this
        initRecyclerView()
        observeBooks()

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    println("query${query}입니다.")
                    searchViewModel.keyword.value = query
                    searchViewModel.searching2()
                }
                searchView.clearFocus() // 키보드를 숨깁니다.
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println("newText${newText}입니다.")
                searchViewModel.keyword.value = newText
                return false
            }
        })

        binding.tvReKeyword.setOnClickListener {
            //화면 이동!
            startActivity(Intent(this,RecentKeywordActivity::class.java))
//            val intent = Intent(this, RecentKeywordActivity::class.java)
//            startActivity(intent)
        }

    }
    private fun observeBooks() {
        println("observeBooks 실행!")
        booksAdapter = SearchBooksAdapter(mutableListOf())
        binding.recyclerview.adapter = booksAdapter
    }

    private fun initRecyclerView() {
        println("initRecyclerView 실행!")

        // 리사이클러뷰 레이아웃 매니저 설정
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        searchViewModel.books.observe(this) { books ->
            booksAdapter.updateBooks(books)
        }
    }

}
