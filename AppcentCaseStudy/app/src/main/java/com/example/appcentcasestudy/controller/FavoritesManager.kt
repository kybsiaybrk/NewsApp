package com.example.appcentcasestudy.controller
import com.example.appcentcasestudy.model.DataClasses.Article

object FavoritesManager {

    val favorites = mutableListOf<Article>()

    fun addFavorite(article: Article) {
        if (!favorites.any { it.url == article.url }) {
            favorites.add(article)
        }
    }

    fun removeFavorite(article: Article) {
        favorites.removeAll { it.url == article.url }
    }

    fun isFavorite(article: Article): Boolean {
        return favorites.any { it.url == article.url }
    }

}