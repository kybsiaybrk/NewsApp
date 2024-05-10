package com.example.appcentcasestudy.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.appcentcasestudy.controller.FavoritesManager
import com.example.appcentcasestudy.R
import com.example.appcentcasestudy.model.DataClasses

class NewsDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail2)

        //xml değişkenleri
        val imageView = findViewById<ImageView>(R.id.newsImageView)
        val titleView = findViewById<TextView>(R.id.newsTitleView)
        val contentView = findViewById<TextView>(R.id.newsContentView)
        val buttonSource = findViewById<Button>(R.id.openSourceButton)
        val buttonBack = findViewById<Button>(R.id.backButton)
        val buttonFavori = findViewById<Button>(R.id.likeButton)
        val shareButton = findViewById<Button>(R.id.ShareButton)


        //intent verileri için değişkenler
        val title = intent.getStringExtra("title") ?: ""
        val imageUrl = intent.getStringExtra("imageUrl") ?: ""
        val content = intent.getStringExtra("content") ?: ""
        val url = intent.getStringExtra("url") ?: ""
        val author = intent.getStringExtra("author") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val publishedAt = intent.getStringExtra("publishedAt") ?: ""


        titleView.text = title
        contentView.text = content
        Glide.with(this).load(imageUrl).into(imageView)


        //webview ile görüntülenen haber kaynak sayfasını içeren activtye gitmek için
        buttonSource.setOnClickListener {
            val intent = Intent(this, NewsSourceActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }


        //main'e gider
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        // bu satırdan itibaren haber favorileme ve paylaşma ile alakalı işlemler var
        //dataclass article constructor
        val article = DataClasses.Article(
            source = DataClasses.Source("", ""),
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = imageUrl,
            publishedAt = publishedAt,
            content = content
        )


        updateFavoriteButton(article)

        buttonFavori.setOnClickListener {
            if (FavoritesManager.isFavorite(article)) {
                FavoritesManager.removeFavorite(article)
            } else {
                FavoritesManager.addFavorite(article)
            }
            updateFavoriteButton(article)
        }



        shareButton.setOnClickListener {
            shareArticleUrl(url)
        }
    }

    private fun updateFavoriteButton(article: DataClasses.Article) {
        val imgFavOrNot = findViewById<ImageView>(R.id.favOrNot)
        if (FavoritesManager.isFavorite(article)) { //isFavorite 'FavoritesManager' objesinin metodu
            imgFavOrNot.setImageResource(R.drawable.kalp)
        } else {
            imgFavOrNot.setImageResource(R.drawable.iciboskalp)
        }
    }

    private fun shareArticleUrl(url: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Haber linkini paylaş: $url")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Haber linkini paylaş"))
    }






}
