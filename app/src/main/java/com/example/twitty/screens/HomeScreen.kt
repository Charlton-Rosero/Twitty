package com.example.twitty.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lib_data.domain.models.Post
import com.example.lib_data.util.Resource
import com.example.twitty.R
import com.example.twitty.screens.destinations.CreatePostDestination
import com.example.twitty.screens.destinations.LoginScreenDestination
import com.example.twitty.screens.destinations.PostScreenDestination
import com.example.twitty.ui.theme.Gray
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
    println("POST OVER HERE $some")
    Column(
        modifier = Modifier
            .background(Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(Gray),
            horizontalArrangement = Arrangement.SpaceBetween

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
        if(some != null){
            LazyColumn(
                modifier = Modifier
                    .height(700.dp)
            ) {
               when(some){
                   is Resource.Error -> {}
                   Resource.Loading -> {}
                   is Resource.Success ->
                      items(some.data){ something ->
                          Stuff(something, navigator)
                      }
               }

            }
        }
        Button(onClick = {
            navigator.navigate(CreatePostDestination.route)
        }) {
                Text(text = "Add Post")
        }
    }
}


@Composable
fun Stuff(
    data: Post,
    navigator: DestinationsNavigator
){

    Card(
        modifier = Modifier
            .width(410.dp)
            .height(150.dp)
            .clickable {
                navigator.navigate(PostScreenDestination(data.id.toString()))
            }
    ) {
        Column(
            modifier = Modifier.width(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "@${data.username}"
            )
            Text(
                text = data.content)
        }

    }

}

