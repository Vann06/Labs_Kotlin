package com.example.laboratorio7_retrofit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.laboratorio7_retrofit.ui.categories.view.MealsCategoriesScreen
import com.example.laboratorio7_retrofit.ui.categories.viewModel.MealsCategoriesViewModel
import com.example.laboratorio7_retrofit.ui.market.view.AddItemScreen
import com.example.laboratorio7_retrofit.ui.market.view.SupermarketListScreen
import com.example.laboratorio7_retrofit.ui.market.viewModel.SupermarketViewModel
import com.example.laboratorio7_retrofit.ui.mealdetail.view.MealsDetailScreen
import com.example.laboratorio7_retrofit.ui.meals.view.MealsFilterScreen

import androidx.activity.result.ActivityResultLauncher
import android.content.Intent

@Composable
fun Navigation(
    navController: NavHostController,
    mealViewModel: MealsCategoriesViewModel,
    supermarketViewModel: SupermarketViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationState.MealsCategories.route,
        modifier = modifier
    ) {
        // MealsCategoriesScreen
        composable(route = NavigationState.MealsCategories.route) {
            MealsCategoriesScreen(
                navController = navController,
                viewModel = mealViewModel
            )
        }

        // SupermarketScreen
        composable(route = NavigationState.Supermarket.route) {
            SupermarketListScreen(
                viewModel = supermarketViewModel,
                navController = navController
            )
        }

        // AddItemScreen
        composable(route = "addItem") {
            AddItemScreen(
                viewModel = supermarketViewModel,
                navController = navController
            )
        }

        // MealFilterScreen
        composable(
            route = NavigationState.MealsRecipesList.route,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("category") ?: ""
            MealsFilterScreen(
                navController = navController,
                category = categoryName
            )
        }

        // MealDetailScreen
        composable(
            route = NavigationState.MealDetail.route,
            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            MealsDetailScreen(
                navController = navController,
                mealId = mealId
            )
        }
    }
}
