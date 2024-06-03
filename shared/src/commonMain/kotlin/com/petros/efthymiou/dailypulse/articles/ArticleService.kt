package com.petros.efthymiou.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(private val httpClient: HttpClient) {

    private val query = "business"
    private val apiKey = "b085a01b01c44e71b378dcf8c85bc295"

    suspend fun getArticles(): List<ArticleRaw> {
        val response: ArticleResponse =
            httpClient.get(
                "https://newsapi.org/v2/everything?q=$query&sortBy=popularity&apiKey=$apiKey"
            ).body()
        return response.articles ?: emptyList()
    }

}