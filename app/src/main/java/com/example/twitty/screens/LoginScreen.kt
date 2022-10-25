package com.example.twitty.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import com.example.twitty.R
import com.example.twitty.screens.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator



//
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
){
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
        var password by remember {
            mutableStateOf("")
        }
        TextField(value = password ,
            onValueChange = {password = it},
            placeholder = { Text(text = "password")},
        )
        Button(onClick = {
            navigator.navigate(HomeScreenDestination.route)
        }) {
            Text("Login")
        }

    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun ComposablePreview() {
//   LoginScreen(navigator=DestinationsNavigator())
}
