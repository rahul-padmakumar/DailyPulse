package com.petros.efthymiou.dailypulse.android.di

import com.petros.efthymiou.dailypulse.articles.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        ArticleViewModel(get())
    }
}