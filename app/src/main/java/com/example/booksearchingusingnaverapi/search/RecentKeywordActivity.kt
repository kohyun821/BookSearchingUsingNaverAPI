package com.example.booksearchingusingnaverapi.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.booksearchingusingnaverapi.databinding.ActivityRecentKeywordBinding


class RecentKeywordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentKeywordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentKeywordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appbar.setNavigationOnClickListener {
            finish()
        }
    }
}