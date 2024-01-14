package net.estemon.studio.setzet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.estemon.studio.setzet.ui.navigation.NavigationGraph
import net.estemon.studio.setzet.ui.screens.HomeScreen
import net.estemon.studio.setzet.ui.screens.activities.ChooseActivityScreen
import net.estemon.studio.setzet.ui.screens.init.LoginScreen
import net.estemon.studio.setzet.ui.screens.init.SplashScreen
import net.estemon.studio.setzet.ui.theme.SetZetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetZetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavigationGraph(navController)
}