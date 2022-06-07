package com.example.studikasus7chapter8.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.studikasus7chapter8.model.GetAllNewsResponseItem
import com.example.studikasus7chapter8.view.ui.theme.StudiKasus7Chapter8Theme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudiKasus7Chapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val news = intent.getSerializableExtra("DATANEWS") as GetAllNewsResponseItem
                    DisplayDetail(news)
                }
            }
        }
    }
}

@Composable
fun DisplayDetail(news: GetAllNewsResponseItem) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = rememberImagePainter(data = news.image),
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Title: \n${news.title}",
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = "Author: \n${news.author}",
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = "Created at: \n${news.createdAt}",
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = "Desc: \n${news.description}",
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 10.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    StudiKasus7Chapter8Theme {
        DisplayDetail(
            news = GetAllNewsResponseItem(
                "author",
                "when",
                "desc",
                "1",
                "http://placeimg.com/640/480",
                "title"
            )
        )
    }
}