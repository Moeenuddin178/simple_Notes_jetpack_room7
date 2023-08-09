package com.example.simple_notes_jetpack_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simple_notes_jetpack_room.ui.home.HomeScreen
import com.example.simple_notes_jetpack_room.ui.home.NoteScreen
import com.example.simple_notes_jetpack_room.ui.theme.Simple_Notes_jetpack_roomTheme
import com.example.simple_notes_jetpack_room.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

private enum class Screens {
    Home, Note
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            Simple_Notes_jetpack_roomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(navController = navController, startDestination = Screens.Home.name) {
                        composable(Screens.Home.name) {
                            HomeScreen(homeViewModel, onClickNote = {
                                navController.navigate(Screens.Note.name)
                            })
                        }
                        composable(Screens.Note.name) {
                            NoteScreen(homeViewModel,
                                onClickClose = { navController.popBackStack() })
                        }

                    }

                }
            }
        }
    }


}
