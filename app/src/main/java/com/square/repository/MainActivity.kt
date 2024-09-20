package com.square.repository

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.square.repository.ui.screen.repository_list.SquareRepositoryListScreen
import com.square.repository.ui.theme.SquareRepositoryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SquareRepositoryTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = SquareRepositoryRoute
                ) {
                    composable<SquareRepositoryRoute> {
                        SquareRepositoryListScreen()
                    }
                }
            }
        }
    }
}

@Serializable
data object SquareRepositoryRoute

