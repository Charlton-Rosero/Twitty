package com.example.twitty.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lib_data.util.Resource
import com.example.twitty.R
import com.example.twitty.screens.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CreatePost(
    navigator: DestinationsNavigator,
    viewModel: CreatePostViewModel = hiltViewModel()
){
    val newPost = viewModel.createPost.collectAsState().value
    LaunchedEffect(key1 = newPost) {
        when (newPost) {
            is Resource.Error -> println("CustomPost Error!")
            Resource.Loading -> println("CustomPost Loading...")
            is Resource.Success -> navigator.navigate(HomeScreenDestination)
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
        var content by remember {
            mutableStateOf("")
        }
        TextField(
            value = content,
            modifier = Modifier
                .width(280.dp)
                .height(400.dp),
            onValueChange = {content = it},
            placeholder = { Text(text = "Content") },
        )
        //
        val context = LocalContext.current
        Button(onClick = {
            if (content != ""){
                viewModel.createPost(post = content)
                navigator.navigate(HomeScreenDestination.route)
            }
            else{
                Toast.makeText(context,"Cannot be empty",Toast.LENGTH_SHORT).show();
            }
        }) {
            Text("Add Post")
        }

    }
}
