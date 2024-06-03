package com.petros.efthymiou.dailypulse.articles

import com.petros.efthymiou.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel(private val articleUseCase: ArticleUseCase): BaseViewModel() {
    private val _state: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState())
    val state: StateFlow<ArticleState> = _state

    init {
        loadData(false)
    }

    fun loadData(forceRefresh: Boolean){
        println("Refrsh called: $forceRefresh")
        scope.launch {
            _state.update {
                it.copy(isLoading = forceRefresh.not(), isRefreshing = it.isLoading.not() && forceRefresh)
            }
            val data = articleUseCase.getArticles(forceRefresh)
            _state.update {
                it.copy(articles = data, isLoading = false, isRefreshing = false)
            }
        }
    }
}