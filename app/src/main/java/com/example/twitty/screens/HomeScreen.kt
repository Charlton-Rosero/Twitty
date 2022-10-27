package com.example.twitty.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.twitty.R
import com.example.twitty.screens.destinations.LoginScreenDestination
import com.example.twitty.screens.destinations.PostScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


/**
 *
 */

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    val some = viewModel.post.collectAsState().value
    println(some)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {

            Image(
                painterResource(R.drawable.twitterlogo),
                modifier = Modifier.size(55.dp),
                contentDescription = "logo"
            )
            Button(
                onClick = { navigator.navigate(LoginScreenDestination.route) }
            ) {
                Text(text = "Logout")
            }

        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item { Dummy(navigator) }

        }
    }
}


@Composable
fun Dummy(
    navigator: DestinationsNavigator
){
    Card(
        modifier = Modifier.clickable { navigator.navigate(PostScreenDestination.route) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Home Screen", textAlign = TextAlign.Center)
        }

    }
}
