package com.example.studikasus7chapter8.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studikasus7chapter8.R
import com.example.studikasus7chapter8.model.RequestUser
import com.example.studikasus7chapter8.view.ui.theme.StudiKasus7Chapter8Theme
import com.example.studikasus7chapter8.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudiKasus7Chapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DisplayRegisterUserInterface()
                }
            }
        }
    }
}

@Composable
fun DisplayRegisterUserInterface() {
    val mContext = LocalContext.current
    val viewModelUser = viewModel(modelClass = UserViewModel::class.java)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var nama by remember {
            mutableStateOf("")
        }
        var alamat by remember {
            mutableStateOf("")
        }
        var umur by remember {
            mutableStateOf(0)
        }
        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var konfirmasiPassword by remember {
            mutableStateOf("")
        }

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "img",
            modifier = Modifier.padding(bottom = 20.dp)
        )
        TextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text(text = "Input nama") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = alamat,
            onValueChange = { alamat = it },
            label = { Text(text = "Input alamat") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = umur.toString(),
            onValueChange = { umur = it.toInt() },
            label = { Text(text = "Input umur") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Input username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Input password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = konfirmasiPassword,
            onValueChange = { konfirmasiPassword = it },
            label = { Text(text = "Input konfirmasi password") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            viewModelUser.insertNewUser(
                RequestUser(
                    alamat,
                    "https://loremflickr.com/640/480",
                    nama,
                    password,
                    umur,
                    username
                )
            )
            Toast.makeText(mContext, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
            mContext.startActivity(Intent(mContext, LoginActivity::class.java))
        }, modifier = Modifier.padding(top = 20.dp)) {
            Text(text = "REGISTER")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    StudiKasus7Chapter8Theme {
        DisplayRegisterUserInterface()
    }
}