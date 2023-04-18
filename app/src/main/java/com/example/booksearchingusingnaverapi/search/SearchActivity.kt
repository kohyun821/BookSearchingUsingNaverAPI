package com.example.booksearchingusingnaverapi.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        // RecyclerView 초기화
        initRecyclerView()

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

        //최근 검색어 화면 이동!
        binding.tvReKeyword.setOnClickListener {
            startActivity(Intent(this,RecentKeywordActivity::class.java))
        }

    }

    private fun initRecyclerView() {
        println("initRecyclerView 실행!")
        booksAdapter = SearchBooksAdapter(mutableListOf(),object : SearchBooksAdapter.OnItemClickListener{
            override fun onItemClick(bookItem: BookItem) {
                val intent = Intent(this@SearchActivity, WebviewActivity::class.java)
                intent.putExtra("url", bookItem.link)
                startActivity(intent)
            }
        })
        binding.recyclerview.adapter = booksAdapter

        // 리사이클러뷰 레이아웃 매니저 설정
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        searchViewModel.bookItems.observe(this) { books ->
            booksAdapter.updateBooks(books)
        }
    }

}
