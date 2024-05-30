package com.petros.efthymiou.dailypulse.articles

import com.petros.efthymiou.dailypulse.ErrorState
import com.petros.efthymiou.dailypulse.LoadingState

data class ArticleState(
    val articles: List<Article> = emptyList(),
    override val status: Int = 200,
    override val errorMessage: String? = null,
    override val errorTitle: String? = "",
    override val isLoading: Boolean = false
): LoadingState, ErrorState