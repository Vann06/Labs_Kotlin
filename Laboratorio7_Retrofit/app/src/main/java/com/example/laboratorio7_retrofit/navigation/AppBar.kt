package com.example.laboratorio7_retrofit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(text = title, color = Color.White)
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50)),
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("supermarketList")
            }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Go to Supermarket"
                )
            }
        }
    )
}
