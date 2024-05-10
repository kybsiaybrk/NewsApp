package com.example.appcentcasestudy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentcasestudy.R
import com.example.appcentcasestudy.model.DataClasses.Article
import com.bumptech.glide.Glide

class NewsAdapter(var articles: List<Article>, private val onClick: (Article) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view, onClick) 
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size //kaç tane viewholder oluşacak

    class NewsViewHolder(itemView: View, private val onClick: (Article) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(article: Article) {
            titleTextView.text = article.title
            Glide.with(itemView.context).load(article.urlToImage).into(imageView)

            itemView.setOnClickListener {
                onClick(article)  // Tıklama olayında Article nesnesini geçir
            }
        }
    }
}
