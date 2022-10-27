package com.example.twitty.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lib_data.domain.models.Comment
import com.example.lib_data.domain.models.Post
import com.example.lib_data.util.Resource
import com.example.twitty.screens.destinations.PostScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PostScreen(
    id: String,
    viewModel: PostScreenViewModel = hiltViewModel()
) {
    val newId = id.toLong()
    viewModel.getComment(newId)
    val comments = viewModel.comment.collectAsState().value
    LaunchedEffect(key1 = comments) {
        when (comments) {
            is Resource.Error -> {}
            Resource.Loading -> {}
            is Resource.Success -> println("Comments ${comments.data}")
            null -> {}
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .border(width = 10.dp, color = Color.Gray),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "@username"
                )
            }
            Text("THIS is a Post")
        }
        if (comments != null) {
            LazyColumn(
                modifier = Modifier
                    .height(700.dp)
            ) {


                when (comments) {
                    is Resource.Error -> {}
                    Resource.Loading -> {}
                    is Resource.Success ->
                        items(comments.data) { comments ->
                            println("I AM A COMMENT ${comments.content}")

                        }
                }
            }
        }

    }
}

@Composable
fun Stuff(
    data: Comment
) {

    Card(
        modifier = Modifier
            .width(1000.dp)
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "@${data.username}"
            )
            Text(
                text = data.content
            )
        }
    }

}
