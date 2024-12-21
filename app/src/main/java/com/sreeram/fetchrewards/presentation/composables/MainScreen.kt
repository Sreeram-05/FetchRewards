package com.sreeram.fetchrewards.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sreeram.fetchrewards.R
import com.sreeram.fetchrewards.data.model.Item
import com.sreeram.fetchrewards.presentation.ViewModel.FetchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Main Screen") },
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            ItemScreen(navHostController)
            CommonButton(
                label = stringResource(R.string.sort_results),
                onClick = { navHostController.navigate("sortTheResultsScreen") }
            )
            CommonButton(
                label = stringResource(R.string.filter_items_or_final_list),
                onClick = { navHostController.navigate("filterOutAnyItemsScreen") }
            )
        }
    }
}

@Composable
fun CommonButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
    ) {
        Text(text = label)
    }
}

@Composable
fun ItemScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(18.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            (1..4).forEach { listId ->
                Button(onClick = {
                    navController.navigate("list/$listId")
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("ListId: $listId")
                }
                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }
}