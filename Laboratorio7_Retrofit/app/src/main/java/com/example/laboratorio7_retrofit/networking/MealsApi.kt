package com.example.laboratorio7_retrofit.networking;

import com.example.laboratorio7_retrofit.networking.response.categories.MealsCategoriesResponse
import com.example.laboratorio7_retrofit.networking.response.mealdetail.MealDetailResponse
import com.example.laboratorio7_retrofit.networking.response.meals.MealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    // 1. Obtener todas las categorías
    @GET("categories.php")
    suspend fun getMealsCategories(): MealsCategoriesResponse


    // 2. Filtrar recetas por categoría
    @GET("filter.php")
    suspend fun filterByCategory(
        @Query("c") category: String
    ): MealsResponse

    // 3. Obtener detalles de la receta por id
    @GET("lookup.php")
    fun getMealDetail(
        @Query("i") mealId: String
    ): Call<MealDetailResponse>
}
