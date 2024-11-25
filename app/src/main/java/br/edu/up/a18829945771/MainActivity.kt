package br.edu.up.a18829945771

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.a18829945771.screens.CreateScreen
import br.edu.up.a18829945771.screens.MainScreen
import br.edu.up.a18829945771.screens.SelectedItemViewModel
import br.edu.up.a18829945771.screens.UpdateScreen
import br.edu.up.a18829945771.ui.theme.AppA18829945771Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppA18829945771Theme {
                NavigationGraph()
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    val selectedItemViewModel: SelectedItemViewModel = viewModel()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController, selectedItemViewModel) }
        composable("create") { CreateScreen(navController) }
        composable("update") { UpdateScreen(navController, selectedItemViewModel) }
    }
}