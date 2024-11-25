package br.edu.up.a18829945771.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.edu.up.a18829945771.data.Item


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    nav: NavController,
    selectedItemViewModel: SelectedItemViewModel,
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {
    val items by mainScreenViewModel.items.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton (onClick = { nav.navigate("create") }) {
                Icon(Icons.Default.Add, contentDescription = "Add")

            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            ItemList(items)
        }
    }

}

@Composable
fun ItemList(items: List<Item>) {
    LazyColumn {
        items(items) {
            Box(modifier = Modifier.height(40.dp).padding(8.dp)){
                Text(text = it.name)
            }

        }
    }
}

