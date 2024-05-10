package com.example.appcentcasestudy.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentcasestudy.controller.FavoritesManager
import com.example.appcentcasestudy.R
import com.example.appcentcasestudy.adapter.NewsAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoritesActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        enableEdgeToEdge()

        recyclerView = findViewById(R.id.recyclerViewFavorite)
        navView = findViewById(R.id.navigation)
        setupNavigation()

        adapter = NewsAdapter(FavoritesManager.favorites) { article ->
            // Burada her bir haber öğesi için yapılacak işlem, örneğin haber detay sayfasını açmak
            val intent = Intent(this, NewsDetailActivity::class.java).apply {
                putExtra("title", article.title)
                putExtra("imageUrl", article.urlToImage)
                putExtra("content", article.content)
                putExtra("url", article.url)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()  // Favori listesindeki değişiklikleri güncellemek için
    }


    //maindekiyle aynı sayılır
    private fun setupNavigation() {
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // main'e
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_favorites -> {
                    true
                }
                else -> false
            }
        }
    }
}
