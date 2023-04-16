package com.example.booksearchingusingnaverapi.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    var keyword: MutableLiveData<String> = MutableLiveData("")

    var inputKeyword : MutableLiveData<Boolean> = MutableLiveData(false)

}