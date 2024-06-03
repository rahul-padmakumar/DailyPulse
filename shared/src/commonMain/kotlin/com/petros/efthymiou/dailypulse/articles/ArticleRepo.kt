package com.petros.efthymiou.dailypulse.articles

interface ArticleRepo {
    suspend fun getArticles(forceRefresh: Boolean):List<ArticleRaw>
}