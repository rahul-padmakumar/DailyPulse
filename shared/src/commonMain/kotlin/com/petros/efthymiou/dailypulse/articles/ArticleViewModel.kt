package com.petros.efthymiou.dailypulse.articles

import com.petros.efthymiou.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel: BaseViewModel() {
    private val _state: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState())
    val state: StateFlow<ArticleState> = _state

    init {
        loadData()
    }

    private fun loadData(){
        scope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val data = mockData()
            delay(500)
            _state.update {
                it.copy(articles = data, isLoading = false)
            }
        }
    }

    private fun mockData(): List<Article> = listOf(
        Article(
            title = "News Title1",
            subTitle = "News subtitle1",
            date = "Jun 22, 2024",
            imageUrl = "https://media.wired.com/photos/66565b792a8564947bc5ed92/191:100/w_1280,c_limit/Marvel-Apple-Vision-Pro-Culture-WhatIfImmersive_Screenshot_DangerousVariants.jpg"
        ),
        Article(
            title = "News Title2",
            subTitle = "News subtitle2",
            date = "Jun 22, 2024",
            imageUrl = "https://media.wired.com/photos/66565b792a8564947bc5ed92/191:100/w_1280,c_limit/Marvel-Apple-Vision-Pro-Culture-WhatIfImmersive_Screenshot_DangerousVariants.jpg"
        ),
        Article(
            title = "News Title3",
            subTitle = "News subtitle3",
            date = "Jun 22, 2024",
            imageUrl = "https://media.wired.com/photos/66565b792a8564947bc5ed92/191:100/w_1280,c_limit/Marvel-Apple-Vision-Pro-Culture-WhatIfImmersive_Screenshot_DangerousVariants.jpg"
        )
    )
}