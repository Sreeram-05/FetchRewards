package com.sreeram.fetchrewards.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sreeram.fetchrewards.R
import com.sreeram.fetchrewards.data.model.Item
import com.sreeram.fetchrewards.presentation.ViewModel.FetchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilteredItemsScreen(
    navController: NavHostController,
    viewModel: FetchViewModel = hiltViewModel()
) {
    val items by viewModel.filterTheResults.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.filtered_or_final_items)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.content_desc_back)
                        )
                    }
                }
            )
        }
    )
    { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when {
                loading -> LoadingIndicator()
                error != null -> Text("Error: $error")
                items.isEmpty() -> EmptyState()
                else ->
                    FilteredItemsCard(items = items)

            }
        }
    }
}

@Composable
fun FilteredItemsCard(items: Map<Int?, List<Item>>) {
    // Sort items by listId and then by name within each list
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items.entries.sortedBy { it.key }) { (listId, itemList) ->
            // Display header for each listId
            ListHeader(listId = listId)

            // Display the sorted items under each listId
            itemList.sortedWith(compareBy<Item> { it.listId }.thenBy { it.name ?: "" })
                .forEach { item ->
                    ItemCard(item)
                }
        }
    }
}

@Composable
fun ItemList(items: Map<Int?, List<Item>>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { (listId, items) ->
            item {
                ListHeader(listId = listId)
            }
            items(items) { item ->
                ItemCard(item = item)
            }
        }
    }
}


@Composable
fun ListHeader(listId: Int?) {
    Text(
        fontWeight = FontWeight.Bold,
        text = "List ID: $listId",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
    Spacer(modifier = Modifier.height(12.dp))
}