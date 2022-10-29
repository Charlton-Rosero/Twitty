package com.example.twitty.screens.home_screen

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
import com.example.lib_data.domain.models.datastore.DataSource
import com.example.lib_data.util.Resource
import com.example.twitty.R
import com.example.twitty.screens.destinations.CreatePostDestination
import com.example.twitty.screens.destinations.EditPostDestination
import com.example.twitty.screens.destinations.LoginScreenDestination
import com.example.twitty.screens.destinations.PostScreenDestination
import com.example.twitty.ui.theme.Gray
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    var some = viewModel.post.collectAsState().value
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
            SwipeToRefreshFunction( navigator = navigator, viewModel )

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
    navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    val store: DataSource
    Card(
        modifier = Modifier
            .width(410.dp)
            .height(150.dp)
            .clickable {
                navigator.navigate(PostScreenDestination(data.id.toString()))
            }
    ) {
        Row(
            modifier = Modifier.width(410.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .width(410.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "@${data.username}"
                )
                Text(
                    text = data.content.toString())
            }
            Column(
                modifier = Modifier
                    .width(80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End

            ) {
                Button(
                    onClick = {
                        viewModel.deletePost(data.id.toLong(), data.username)
                    }) {
                    Text(text = "Delete")
                }
                Button(
                    onClick = { navigator.navigate(EditPostDestination(data.id.toString(),data.content, data.username))
                    }) {
                    Text(text = "Edit")
                }

            }

        }


    }

}

@Composable
fun SwipeToRefreshFunction(
    navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    var some = viewModel.post.collectAsState().value
    val isRefreshing = viewModel.isRefreshing.collectAsState().value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::refresh,
    ) {
        LazyColumn(
            modifier = Modifier
                .height(700.dp)
        ) {
            when (some) {
                is Resource.Error -> {}
                Resource.Loading -> {}
                is Resource.Success ->
                    items(some.data) { something ->
                        Stuff(something, navigator, viewModel)
                    }
                null -> {}
            }

        }

    }
}


