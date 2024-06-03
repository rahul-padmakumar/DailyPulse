package com.petros.efthymiou.dailypulse.articles

class ArticleRepoImpl(
    private val articleService: ArticleService,
    private val articleDataSource: ArticleDataSource
): ArticleRepo{
    override suspend fun getArticles(forceRefresh: Boolean):List<ArticleRaw>{
        if(forceRefresh){
            return fetchArticles()
        }

        val cachedArticles = articleDataSource.getArticles()

        if(cachedArticles.isEmpty()){
            return fetchArticles()
        }

        return cachedArticles
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val articles = articleService.getArticles()
        articleDataSource.insertArticles(articles)
        return articles
    }
}