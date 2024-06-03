package com.petros.efthymiou.dailypulse.articles

import petros.efthymiou.dailypulse.db.DailyPulseDatabase

class ArticleDataSource(private val database: DailyPulseDatabase) {

    fun getArticles(): List<ArticleRaw> = database.dailyPulseDatabaseQueries.query { title, desc, date, imageUrl ->
        mapToArticleRaw(title, desc, date, imageUrl)
    }.executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) = database.dailyPulseDatabaseQueries.transaction {
        articles.forEach { articleRaw ->
            database.dailyPulseDatabaseQueries.insert(
                articleRaw.title,
                articleRaw.description,
                articleRaw.date,
                articleRaw.imageUrl
            )
        }
    }

    fun removeAll() = database.dailyPulseDatabaseQueries.removeAll()


    private fun mapToArticleRaw(
        title: String?,
        desc: String?,
        date: String?,
        imageUrl: String?
    ) = ArticleRaw(
        title,
        desc,
        imageUrl,
        date
    )
}