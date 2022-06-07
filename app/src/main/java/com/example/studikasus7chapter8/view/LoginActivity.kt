package com.example.studikasus7chapter8.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studikasus7chapter8.R
import com.example.studikasus7chapter8.datastore.UserLoginManager
import com.example.studikasus7chapter8.view.ui.theme.StudiKasus7Chapter8Theme
import com.example.studikasus7chapter8.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudiKasus7Chapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val mContext = LocalContext.current
                    val userLoginManager = UserLoginManager(mContext)
                    userLoginManager.boolean.asLiveData().observe(this) {
                        if (it == true) {
                            mContext.startActivity(Intent(mContext, MainActivity::class.java))
                        }
                    }
                    DisplayLoginUserInterface()
                }
            }
        }
    }
}

@Composable
fun DisplayLoginUserInterface() {
    val mContext = LocalContext.current
    val viewModelUser = viewModel(modelClass = UserViewModel::class.java)
    val dataUser by viewModelUser.dataUserState.collectAsState()
    val userLoginManager = UserLoginManager(mContext)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "img",
            modifier = Modifier.padding(bottom = 20.dp)
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
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    for (i in dataUser.indices) {
                        if (username == dataUser[i].username && password == dataUser[i].password) {
                            GlobalScope.launch {
                                userLoginManager.setBoolean(true)
                                userLoginManager.saveDataLogin(
                                    dataUser[i].address,
                                    dataUser[i].name,
                                    dataUser[i].password,
                                    dataUser[i].umur,
                                    dataUser[i].username
                                )
                            }
                            Toast.makeText(mContext, "Login berhasil", Toast.LENGTH_SHORT).show()
                            mContext.startActivity(Intent(mContext, MainActivity::class.java))
                        }
                    }
                }

            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = "Login")
        }
        Text(
            text = "Register here",
            Modifier.clickable {
                mContext.startActivity(
                    Intent(
                        mContext,
                        RegisterActivity::class.java
                    )
                )
            })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    StudiKasus7Chapter8Theme {
        DisplayLoginUserInterface()
    }
}