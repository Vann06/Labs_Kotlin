package com.example.laboratorio7_retrofit.navigation;


sealed class NavigationState(val route: String) {
    data object MealsCategories: NavigationState("categories")

    data object MealsRecipesList: NavigationState("categories/{category}")  {
        fun createRoute(category: String) = "categories/$category"
    }
    data object MealDetail: NavigationState("mealDetail/{mealId}") {
        fun createRoute(mealId: String) = "mealDetail/$mealId"
    }
    data object Home: NavigationState("home")
    data object Profile: NavigationState("profile")
}