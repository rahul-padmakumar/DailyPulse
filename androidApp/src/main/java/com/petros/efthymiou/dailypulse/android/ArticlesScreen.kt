package com.petros.efthymiou.dailypulse.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticlesScreen(
    articleViewModel: ArticleViewModel = getViewModel(),
    onAboutClicked: () -> Unit
){

    val state = articleViewModel.state.collectAsStateWithLifecycle()

    state.value.errorMessage?.let {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Column {
                Text(state.value.errorTitle ?: "Error")
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                )
                Text(text = it)
            }
        }
    }

    ArticlesUI(state.value, onAboutClicked){
        articleViewModel.loadData(true)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesUI(
    value: ArticleState,
    onAboutClicked: () -> Unit,
    onRefresh: () -> Unit
    ) {

    val pullToRefreshState = rememberPullToRefreshState()
    val onRefreshState = rememberUpdatedState(newValue = onRefresh)

    if(pullToRefreshState.isRefreshing){
        LaunchedEffect(key1 = true) {
            onRefreshState.value()
        }
    }

    LaunchedEffect(key1 = value.isRefreshing) {
        if(value.isRefreshing){
            pullToRefreshState.startRefresh()
        } else {
            pullToRefreshState.endRefresh()
        }
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Column {
            TopAppBar(
                title = {Text("Articles")},
                actions = {
                    IconButton(onClick = onAboutClicked) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
                    }
                }
            )

            Box(modifier = Modifier.fillMaxWidth().nestedScroll(pullToRefreshState.nestedScrollConnection)){
                LazyColumn {
                    items(value.articles){
                        ArticleItemUI(it)
                    }
                }
                if(value.isLoading.not()) {
                    PullToRefreshContainer(
                        state = pullToRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
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
                fontSize = 22.sp,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(article.subTitle ?: "", style = TextStyle(color = Color.Black))
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date ?: "",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
