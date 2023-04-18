package com.example.booksearchingusingnaverapi.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearchingusingnaverapi.databinding.ActivityRecentKeywordBinding

class RecentKeywordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentKeywordBinding
    private lateinit var adapter: RecentKeywordAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentKeywordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appbar.setNavigationOnClickListener {//뒤로가기
            finish()
        }
        adapter = RecentKeywordAdapter(object : OnRecentKeywordClickListener {
            override fun onKeywordClick(keyword: String) {
                val intent = Intent(this@RecentKeywordActivity, SearchActivity::class.java).apply {
                    putExtra("searchKeyword", keyword)
                    println("onKeywordClick 실행 입니다.")
                    println("onKeywordClick 실행으로 ${keyword}을 넘겨 입니다.")
                }
                setResult(RESULT_OK,intent)
                finish()
            }
            override fun onKeywordDelete(recentSearch: RecentSearch) {
                viewModel.deleteRecentSearch(recentSearch)
            }
        })

        binding.rvRecntkeywork.layoutManager = LinearLayoutManager(this)
        binding.rvRecntkeywork.adapter = adapter

        viewModel.recentSearches.observe(this, Observer { recentSearches ->
            adapter.setData(recentSearches)
        })
    }
}
