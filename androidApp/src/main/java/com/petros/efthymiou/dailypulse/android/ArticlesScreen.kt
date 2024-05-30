package com.petros.efthymiou.dailypulse.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.petros.efthymiou.dailypulse.articles.Article
import com.petros.efthymiou.dailypulse.articles.ArticleState
import com.petros.efthymiou.dailypulse.articles.ArticleViewModel

@Composable
fun ArticlesScreen(
    articleViewModel: ArticleViewModel
){

    val state = articleViewModel.state.collectAsStateWithLifecycle()

    state.value.errorMessage?.let {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Column {
                Text(state.value.errorTitle ?: "Error")
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp))
                Text(text = it)
            }
        }
    }

    ArticlesUI(state.value)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesUI(value: ArticleState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        TopAppBar(title = {Text("Articles")})

        LazyColumn {
            items(value.articles){
                ArticleItemUI(it)
            }
        }
    }
}

@Composable
fun ArticleItemUI(article: Article) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            article.title ?: "",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(article.subTitle ?: "")
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date ?: "",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
