//
//  ArticleScreen.swift
//  iosApp
//
//  Created by Rahul Padmakumar on 30/05/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension ArticleScreen{
    @MainActor
    class ArticleViewModelWrapper: ObservableObject{
        let articleViewModel: ArticleViewModel
        
        @Published var articleState: ArticleState
        init() {
            self.articleViewModel = ArticleViewModel()
            self.articleState = articleViewModel.state.value
        }
        
        func startObserving(){
            Task{
                for await articles in articleViewModel.state{
                    self.articleState = articles
                }
            }
        }
    }
}

struct ArticleScreen: View {
    @ObservedObject private(set) var viewModel: ArticleViewModelWrapper
    var body: some View {
        VStack{
            AppBar()
            ZStack{
                if(viewModel.articleState.isLoading){
                    Loader()
                }
                if(!viewModel.articleState.articles.isEmpty){
                    ScrollView{
                        LazyVStack(spacing: 10){
                            ForEach(viewModel.articleState.articles, id:\.self){ article in
                                ArticleItemView(article: article)
                            }
                        }
                    }
                }
            }
        }.onAppear{
            self.viewModel.startObserving()
        }
    }
}

struct ArticleItemView: View{
    var article: Article
    
    var body: some View{
        VStack(alignment: .leading, spacing: 8){
            if let url = article.imageUrl{
                AsyncImage(url: URL(string: url)){ phase in
                    if phase.image != nil {
                        phase.image!.resizable().aspectRatio(contentMode: .fit)
                    } else if phase.error != nil {
                        Text("Image not loaded")
                    } else {
                        ProgressView()
                    }
                }
            }
            Text(article.title ?? "").font(.title).fontWeight(.bold)
            Text(article.subTitle ?? "")
            Text(article.date ?? "").frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }.padding(16)
    }
}

struct AppBar: View{
    var body: some View{
        Text("Articles")
            .font(.largeTitle)
    }
}

struct Loader: View{
    var body: some View{
        ProgressView()
    }
}

