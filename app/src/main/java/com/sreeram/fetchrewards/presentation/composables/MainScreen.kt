package com.sreeram.fetchrewards.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sreeram.fetchrewards.R

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
            CommonButton(
                label = stringResource(R.string.display_all_items),
                onClick = { navHostController.navigate("displayAllItemsScreen") }
            )
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
