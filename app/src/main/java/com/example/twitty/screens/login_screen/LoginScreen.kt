package com.example.twitty.screens.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lib_data.util.Resource
import com.example.twitty.R
import com.example.twitty.screens.destinations.HomeScreenDestination
import com.example.twitty.screens.destinations.SignUpScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator



@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginScreenViewModel = hiltViewModel()
){
    val token = viewModel.user.collectAsState().value
    LaunchedEffect(key1 = token){
        when(token){
            is Resource.Error -> {}
            Resource.Loading -> {}
            is Resource.Success -> login(navigator)
            null -> {}
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.twitterlogo) ,
            contentDescription ="logo" )
        //
        var username by remember {
            mutableStateOf("")
        }
        TextField(value = username ,
            onValueChange = {username = it},
            placeholder = { Text(text = "username")},
        )
        //
        var password by remember {
            mutableStateOf("")
        }
        TextField(value = password ,
            onValueChange = {password = it},
            placeholder = { Text(text = "password")},
        )
        //
        Button(onClick = {
            println("username:$username, password:$password")
            viewModel.loginUser(username,password)
        }) {
            Text("Login")
        }
        ClickableText(
            text = AnnotatedString("New user? Sign up here.") ,
            onClick = {
                navigator.navigate(SignUpScreenDestination)
            },
            style = TextStyle(
                color = Blue,
                fontSize = 26.sp
            )
        )

    }
}

private fun login(navigator: DestinationsNavigator){
    navigator.navigate(HomeScreenDestination.route)
}

