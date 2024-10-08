package com.example.laboratorio7_retrofit.ui.categories.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.laboratorio7_retrofit.navigation.AppBar
import com.example.laboratorio7_retrofit.ui.categories.viewModel.MealsCategoriesViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MealsCategoriesScreen(navController: NavController) {

    val viewModel: MealsCategoriesViewModel = viewModel()
    val meals = viewModel.mealsState.value

    Scaffold(topBar = {
        AppBar(title = "Categories", navController = navController)
    }) {
        Column(modifier = Modifier.fillMaxSize()){
            Spacer(modifier = Modifier.height(70.dp))
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(meals) { meal ->
                    MealCategory(meal, navController)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}