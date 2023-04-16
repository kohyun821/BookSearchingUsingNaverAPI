package com.example.booksearchingusingnaverapi.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.booksearchingusingnaverapi.R
import com.example.booksearchingusingnaverapi.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    lateinit var binding : ActivitySearchBinding
    lateinit var searchViewModel: SearchViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.viewModel = searchViewModel
        binding.lifecycleOwner=this
        binding.activity=this
    }
    fun setObserve(){
        searchViewModel.inputKeyword.observe(this){
            if(it){
                //버튼 클릭이 성공적으로 되었을 경우 화면 이동
            }
        }
    }
    fun searching(){
        println("버튼 클릭!")
        binding.TestTV.text = binding.SearchingEditText.text
        searchViewModel.inputKeyword.value=true
    }
}