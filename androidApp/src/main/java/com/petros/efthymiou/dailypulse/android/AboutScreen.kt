package com.petros.efthymiou.dailypulse.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.petros.efthymiou.dailypulse.Platform

@Composable
fun AboutScreen(onNavigateUp: () -> Unit){
    Surface(
        color = Color.White
    ){
        Column {
            Toolbar(
                title = "About Screen",
                onNavigateUp
            )
            Content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(title: String, onNavigateUp: () -> Unit){
    TopAppBar(
        title = { Text(title)},
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Composable
fun RowView(label: String, value: String){
    Column(modifier = Modifier.padding(8.dp)) {
        Text(style = TextStyle(fontWeight = FontWeight.Bold), text = label)
        Text(text = value)
    }
}

@Composable
fun Content(){
    val data = getData()
    LazyColumn{
        items(data){
            RowView(label = it.first, value = it.second)
        }
    }
}

private fun getData(): List<Pair<String, String>>{
    val platform = Platform()
    platform.logSystemInfo()

    return listOf(
        Pair("OS Info", "${platform.osName} ${platform.osVersion}"),
        Pair("Build Info", platform.deviceModel),
        Pair("Density", platform.deviceDensity.toString())
    )
}