package com.sreeram.fetchrewards.presentation.ui.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sreeram.fetchrewards.presentation.theme.FetchRewardsTheme
import com.sreeram.fetchrewards.presentation.composables.AppHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchRewardsTheme {
                AppHost()
            }
        }
    }
}