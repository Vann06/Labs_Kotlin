package com.example.laboratorio7_retrofit.ui.meals.Respository


import com.example.laboratorio7_retrofit.networking.MealsWebService
import com.example.laboratorio7_retrofit.networking.response.meals.Meal

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun filterByCategory(category: String): List<Meal> {
        return webService.filterMealsByCategory(category).meals
    }
}