package com.example.appcentcasestudy.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.SearchView
import com.example.appcentcasestudy.adapter.NewsAdapter
import com.example.appcentcasestudy.network.RetrofitClient
import com.example.appcentcasestudy.R
import com.example.appcentcasestudy.model.DataClasses
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: NewsAdapter
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        navView = findViewById(R.id.navigation)

        setupRecyclerView()
        setupSearchView()
        setupNavigation() //Tanımlanmış metodların uyg. başladığında çalışabilmesi için burada metodlar çağrıldı.

        getNews("Beşiktaş")  // Anasayfa haberli beşiktaş kelimesine göre gelecek
    }

    //haberlerin başlık ve görseli ile anasayfada görünmesi için
    private fun setupRecyclerView() {
        adapter = NewsAdapter(listOf()) { article ->
            val intent = Intent(this, NewsDetailActivity::class.java).apply {
                putExtra("title", article.title)
                putExtra("imageUrl", article.urlToImage)
                putExtra("content", article.content)
                putExtra("url", article.url)
            }
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    //arama çubuğu işlemleri
    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { getNews(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    // sorgu parametresi göndererek haber verilerini asenkron çeker
    private fun getNews(query: String) {
        val apiKey = "baa1acbe59fe4022885a45eb499aa91c" //NOT: apikey güvene alınmalı
        RetrofitClient.newsService.getNews(query, apiKey).enqueue(object : Callback<DataClasses.NewsResponse> {
            override fun onResponse(call: Call<DataClasses.NewsResponse>, response: Response<DataClasses.NewsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    adapter.articles = response.body()!!.articles
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e("MainActivity", "Response not successful or null")
                }
            }

            override fun onFailure(call: Call<DataClasses.NewsResponse>, t: Throwable) {
                Log.e("MainActivity", "Failed to get news: ${t.message}")
            }
        })
    }


    //navigationbarın backendi burası
    private fun setupNavigation() {
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent) // anasayfa yenilemesi için
                    true
                }
                R.id.navigation_favorites -> {

                    val intent = Intent(this, FavoritesActivity::class.java)
                            startActivity(intent)// Favoriler sayfasına gitmek için
                        true
                }
                else -> false
            }
        }
    }
}
