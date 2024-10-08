package com.example.laboratorio7_retrofit.networking.response.mealdetail

import com.google.gson.annotations.SerializedName

// Respuesta para los detalles de una receta específica
data class MealDetailResponse(
    @SerializedName("meals") val meals: List<MealDetail>
)
