import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearchingusingnaverapi.R
import com.example.booksearchingusingnaverapi.databinding.ActivitySearchBinding
import com.example.booksearchingusingnaverapi.search.BookItem
import com.example.booksearchingusingnaverapi.search.RecentKeywordActivity
import com.example.booksearchingusingnaverapi.search.SearchBooksAdapter
import com.example.booksearchingusingnaverapi.search.SearchViewModel
import com.example.booksearchingusingnaverapi.search.WebviewActivity

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var booksAdapter: SearchBooksAdapter

    private val recentKeywordLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.getStringExtra("searchKeyword")?.let { keyword ->
                println("recentKeywordLauncher 입니다.")
                binding.searchView.setText(keyword)
                searchViewModel.keyword.value = keyword
                searchViewModel.searching3(keyword)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this

        // RecyclerView 초기화
        initRecyclerView()

        val searchView = binding.searchView
        searchView.setOnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val query = searchView.text.toString()
                if (!query.isNullOrEmpty()) {
                    println("query${query}입니다.")
                    searchViewModel.keyword.value = query
                    searchViewModel.searching2()
                }
                searchView.clearFocus() // 키보드를 숨깁니다.
                true
            } else {
                false
            }
        }
        //최근 검색어 화면 이동!
        binding.tvReKeyword.setOnClickListener {
            val intent = Intent(this, RecentKeywordActivity::class.java)
            recentKeywordLauncher.launch(intent)
        }
    }

    private fun initRecyclerView() {
        println("initRecyclerView 실행!")
        //아래 코드는 화면 이동
        booksAdapter = SearchBooksAdapter(mutableListOf(), object : SearchBooksAdapter.OnItemClickListener {
            override fun onItemClick(bookItem:BookItem) {
                val intent = Intent(this@SearchActivity, WebviewActivity::class.java)
                intent.putExtra("url", bookItem.link)
                startActivity(intent)
            }
        })

        binding.recyclerview.adapter = booksAdapter

        // 리사이클러뷰 레이아웃 매니저 설정
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        searchViewModel.bookItems.observe(this) { books ->
            println("변경으로 인한 실행 입니다.")
            booksAdapter.updateBooks(books)
        }
    }
}
