package com.example.twitty


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.twitty.screens.NavGraphs
import com.example.twitty.ui.theme.TwittyTheme
import com.ramcosta.composedestinations.DestinationsNavHost


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








