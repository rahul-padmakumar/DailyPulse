package com.petros.efthymiou.dailypulse.articles.di

import com.petros.efthymiou.dailypulse.articles.ArticleService
import com.petros.efthymiou.dailypulse.articles.ArticleUseCase
import com.petros.efthymiou.dailypulse.articles.ArticleViewModel
import org.koin.dsl.module

val articleModule = module {
    single { ArticleService(get()) }
    single { ArticleUseCase(get()) }
    single { ArticleViewModel(get()) }
}