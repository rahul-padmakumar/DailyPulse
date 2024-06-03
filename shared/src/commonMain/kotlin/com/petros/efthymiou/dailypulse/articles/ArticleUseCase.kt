package com.petros.efthymiou.dailypulse.articles

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticleUseCase(private val articleRep: ArticleRepo) {
    suspend fun getArticles(forceRefresh: Boolean): List<Article> {
        val articleRaw = articleRep.getArticles(forceRefresh)
        return mapArticles(articleRaw)
    }

    private fun mapArticles(articles: List<ArticleRaw>): List<Article> =
        articles.map {
            Article(
                title = it.title,
                subTitle = it.description,
                date = formatDate(it.date),
                imageUrl = it.imageUrl
            )
        }

    private fun formatDate(date: String?): String{
        return date?.let {
            val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
            val days = today.daysUntil(
                Instant.parse(it).toLocalDateTime(TimeZone.currentSystemDefault()).date
            )
            when{
                abs(days) > 1 -> "${abs(days)} days ago"
                abs(days) == 1 -> "yesterday"
                else -> "Today"
            }
        } ?: ""
    }
}
