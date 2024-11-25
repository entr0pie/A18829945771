package br.edu.up.a18829945771.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.edu.up.a18829945771.data.Item
import br.edu.up.a18829945771.data.idGenerator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(nav: NavController, selectedItemViewModel: SelectedItemViewModel, mainScreenViewModel: MainScreenViewModel = viewModel()) {

    val item = selectedItemViewModel.selected.value

    var name by remember { mutableStateOf(item?.name ?: "") }
    var description by remember { mutableStateOf(item?.description ?: "") }

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
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(text = "Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )

                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(text = "Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )

                    Button(
                        onClick = {
                            if (item == null) return@Button
                            val newItem = Item(item.id, name, description)
                            mainScreenViewModel.update(newItem)
                            nav.popBackStack()
                        },
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(text = "Update")
                    }

                    Button(
                        onClick = {
                            if (item == null) return@Button
                            mainScreenViewModel.delete(item)
                            nav.popBackStack()
                        },
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}