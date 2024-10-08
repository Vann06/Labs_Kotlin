package com.example.laboratorio7_retrofit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.laboratorio7_retrofit.ui.categories.view.MealsCategoriesScreen
import com.example.laboratorio7_retrofit.ui.mealdetail.view.MealsDetailScreen
import com.example.laboratorio7_retrofit.ui.meals.view.MealsFilterScreen

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = NavigationState.MealsCategories.route,
        modifier = modifier
    ) {
        // MealCategoriesScreen
        composable(route = NavigationState.MealsCategories.route) {
            MealsCategoriesScreen(navController = navController)
        }
        // MealFilterScreen
        composable(
            route = NavigationState.MealsRecipesList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("category") ?: ""
            MealsFilterScreen(navController = navController, category = categoryName)
        }
        // MealDetailScreen
        composable(
            route = NavigationState.MealDetail.route,
            arguments = listOf(navArgument("mealId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            MealsDetailScreen(navController = navController, mealId = mealId)
        }
    }
}
