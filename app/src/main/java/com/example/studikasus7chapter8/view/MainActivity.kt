package com.example.studikasus7chapter8.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.studikasus7chapter8.R
import com.example.studikasus7chapter8.model.GetAllNewsResponseItem
import com.example.studikasus7chapter8.ui.theme.StudiKasus7Chapter8Theme
import com.example.studikasus7chapter8.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudiKasus7Chapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModelNews = viewModel(modelClass = NewsViewModel::class.java)
                    val dataNews by viewModelNews.dataNewsState.collectAsState()

                    Column(modifier = Modifier.padding(10.dp)) {
                        Image(
                            painterResource(id = R.drawable.ic_baseline_person_24),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.End)
                                .clickable {
//                                    mContext.startActivity(
//                                        Intent(
//                                            mContext,
//                                            StafListActivity::class.java
//                                        )
//                                    )
                                }
                        )
                        LazyColumn {
                            if (dataNews.isEmpty()) {
                                item {

                                }
                            }
                            items(dataNews) {
                                DisplayListNews(news = it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayListNews(news: GetAllNewsResponseItem) {
    val mContext = LocalContext.current
    Column(modifier = Modifier.padding(20.dp)) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    val intent = Intent(mContext, DetailActivity::class.java)
                    intent.putExtra("DATANEWS", news)
                    mContext.startActivity(intent)
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Gray)
            ) {
                Image(
                    painter = rememberImagePainter(data = news.image),
                    contentDescription = "",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
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
                        fontWeight = FontWeight.Normal
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    StudiKasus7Chapter8Theme {
        DisplayListNews(
            news = GetAllNewsResponseItem(
                "patra",
                "6 Juni 2022",
                "ini isi deskripsi",
                "1",
                "http://placeimg.com/640/480",
                "This is a title"
            )
        )
    }
}