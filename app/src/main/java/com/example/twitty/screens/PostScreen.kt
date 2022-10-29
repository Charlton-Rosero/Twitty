package com.example.twitty.screens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lib_data.domain.models.Comment
import com.example.lib_data.domain.models.Post
import com.example.lib_data.util.Resource
import com.ramcosta.composedestinations.annotation.Destination

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
    viewModel.getPostById(newId)
    val post = viewModel.post.collectAsState().value
    LaunchedEffect(key1 = post) {
        when (post) {
            is Resource.Error -> {}
            Resource.Loading -> {}
            is Resource.Success -> println("Comments ${post.data}")
            null -> {}
        }
    }
    val createComment = viewModel.createComment.collectAsState().value
    LaunchedEffect(key1 = createComment) {
        when (createComment) {
            is Resource.Error -> {}
            Resource.Loading -> {}
            is Resource.Success -> println("Comments ${createComment.data}")
            null -> {}
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (post) {
            is Resource.Error -> {}
            Resource.Loading -> {}
            is Resource.Success -> PostDetail(data = post.data)
            null -> {}
        }

        if (comments != null) {
            LazyColumn(
                modifier = Modifier
                    .height(300.dp)
                    .border(10.dp, color = Color.Gray)
            ) {
                when (comments) {
                    is Resource.Error -> {}
                    Resource.Loading -> {}
                    is Resource.Success ->
                        items(comments.data) { comments ->
                            Comment(data = comments)

                        }
                }
            }
        }
        var content by remember {
            mutableStateOf("")
        }
        TextField(
            value = content,
            modifier = Modifier
                .width(280.dp)
                .height(70.dp),
            onValueChange = {content = it},
            placeholder = { Text(text = "Comment") },
        )
        val context = LocalContext.current
        Button(onClick = {

            if(content != ""){
                viewModel.createComment(content = content, postId = newId)
                content = ""
            }
            else{
                Toast.makeText(context,"Cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Add Comment")
        }

    }
}

@Composable
fun Comment(
    data: Comment
) {

    Card(
        modifier = Modifier
            .width(1000.dp)
            .height(70.dp)
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

@Composable
fun PostDetail(
    data: Post
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(width = 10.dp, color = Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "@${data.username}")
        Text(text = data.content)
    }

}
