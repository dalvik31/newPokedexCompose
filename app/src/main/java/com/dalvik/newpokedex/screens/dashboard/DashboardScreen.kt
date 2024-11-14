package com.dalvik.newpokedex.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dalvik.newpokedex.R
import com.dalvik.newpokedex.screens.login.LoginScreen
import com.dalvik.newpokedex.screens.login.LoginViewModel
import com.dalvik.newpokedex.ui.common.customComposableViews.TitleText
import com.dalvik.newpokedex.ui.theme.NewPokedexTheme

@Composable
fun DashboardScreen(dashboardViewModel: DashboardViewModel = hiltViewModel<DashboardViewModel>()) {
    val userName = dashboardViewModel.loginState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText(text = "Welcome user ${userName.value}")
        Button(onClick = { dashboardViewModel.logout() }) {
            
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    NewPokedexTheme {
        DashboardScreen()
    }
}