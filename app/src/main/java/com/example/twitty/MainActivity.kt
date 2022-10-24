package com.example.twitty

import android.graphics.Path.Direction
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.twitty.destinations.HomeScreenDestination
import com.example.twitty.ui.theme.TwittyTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwittyTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}


@RootNavGraph(start = true )
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
){
    val scale = remember{
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue =  0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing ={
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1000L)
        navigator.navigate(HomeScreenDestination.route)
    }
    //Image
    Box(contentAlignment =  Alignment.Center, modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.twitterlogo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))

    }
}

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: MainScreen", textAlign = TextAlign.Center)

    }
}



