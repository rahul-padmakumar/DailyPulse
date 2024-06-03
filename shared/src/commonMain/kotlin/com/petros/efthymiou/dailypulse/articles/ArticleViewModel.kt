package com.petros.efthymiou.dailypulse.articles

import com.petros.efthymiou.dailypulse.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticleViewModel: BaseViewModel() {
    private val _state: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState())
    val state: StateFlow<ArticleState> = _state

    private val articleUseCase: ArticleUseCase

    init {

        val httpClient = HttpClient{
            install(ContentNegotiation){
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        val articleService = ArticleService(httpClient)
        articleUseCase = ArticleUseCase(articleService)
        loadData()
    }

    private fun loadData(){
        scope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val data = articleUseCase.getArticles()
            delay(500)
            _state.update {
                it.copy(articles = data, isLoading = false)
            }
        }
    }
}