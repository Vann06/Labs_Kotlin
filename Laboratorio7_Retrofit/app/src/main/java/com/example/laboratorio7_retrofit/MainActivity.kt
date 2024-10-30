package com.example.laboratorio7_retrofit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio7_retrofit.navigation.AppBar
import com.example.laboratorio7_retrofit.navigation.Navigation
import com.example.laboratorio7_retrofit.navigation.NavigationState
import com.example.laboratorio7_retrofit.ui.categories.view.MealsCategoriesScreen
import com.example.laboratorio7_retrofit.ui.categories.viewModel.MealViewModelFactory
import com.example.laboratorio7_retrofit.ui.categories.viewModel.MealsCategoriesViewModel
import com.example.laboratorio7_retrofit.ui.market.view.SupermarketListScreen
import com.example.laboratorio7_retrofit.ui.market.viewModel.SupermarketViewModel
import com.example.laboratorio7_retrofit.ui.market.viewModel.SupermarketViewModelFactory
import com.example.laboratorio7_retrofit.ui.theme.Laboratorio7_RetrofitTheme

class MainActivity : ComponentActivity() {

    private lateinit var mealViewModel: MealsCategoriesViewModel
    private lateinit var supermarketViewModel: SupermarketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener repositorios desde MyApp
        val mealRepository = (applicationContext as MyApp).categoryRepository
        val supermarketRepository = (applicationContext as MyApp).supermarketRepository

        // Inicializar mealViewModel
        mealViewModel = ViewModelProvider(
            this,
            MealViewModelFactory(mealRepository)
        )[MealsCategoriesViewModel::class.java]

        // Inicializar supermarketViewModel
        supermarketViewModel = ViewModelProvider(
            this,
            SupermarketViewModelFactory(supermarketRepository)
        )[SupermarketViewModel::class.java]

        setContent {
            Laboratorio7_RetrofitTheme {
                Navigation(
                    navController = rememberNavController(),
                    mealViewModel = mealViewModel, // Agregar el mealViewModel aquí
                    supermarketViewModel = supermarketViewModel // Agregar el supermarketViewModel
                )
            }
        }
    }
}
