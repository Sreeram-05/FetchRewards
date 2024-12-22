package com.sreeram.fetchrewards.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sreeram.fetchrewards.R
import com.sreeram.fetchrewards.presentation.ViewModel.FetchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDetailScreen(
    listId: Int?, navController: NavHostController, viewModel: FetchViewModel = hiltViewModel()
) {
    val items by viewModel.listedItems.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val filteredItems = items.filter { it.listId == listId }

    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.sorted_items)) }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.content_desc_back)
                )
            }
        })
    }) { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            when {
                loading -> LoadingIndicator()
                else -> Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Items for List $listId",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredItems) { item ->
                            ItemCard(item)
                        }
                    }
                }
            }
        }
    }
}
