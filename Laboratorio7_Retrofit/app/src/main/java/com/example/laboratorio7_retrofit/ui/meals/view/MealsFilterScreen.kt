package com.example.laboratorio7_retrofit.ui.meals.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.laboratorio7_retrofit.navigation.AppBar
import com.example.laboratorio7_retrofit.ui.meals.viewModel.MealsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MealsFilterScreen(navController: NavController, category: String) {
    Log.d("ARGUMENTS", category)

    val viewModel: MealsViewModel = viewModel()
    val mealFilter by viewModel.meals.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchByCategory(category)
    }

    Scaffold(topBar = {
        AppBar(title = "Recipes", navController = navController)
    }) {
         Column(modifier = Modifier.fillMaxSize()){
             Spacer(modifier = Modifier.height(70.dp))
             LazyColumn(contentPadding = PaddingValues(16.dp)) {
                 mealFilter?.let {
                     items(it) { meal ->
                         MealCategory(meal, navController)
                     }
                 }
             }
         }

    }
}

