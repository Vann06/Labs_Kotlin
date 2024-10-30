package com.example.laboratorio7_retrofit.ui.categories.Repository

import com.example.laboratorio7_retrofit.database.categories.MealCategoryDao
import com.example.laboratorio7_retrofit.database.categories.MealCategoryEntity
import com.example.laboratorio7_retrofit.networking.MealsWebService
import com.example.laboratorio7_retrofit.networking.response.categories.toEntity


class MealsCategoryRepository(private val webService: MealsWebService,
                              private val mealCategoryDao: MealCategoryDao
) {
    suspend fun getMealsCategories(): List<MealCategoryEntity> {
        val entities = webService.getMealsCategories().categories
        val content = entities.map { it.toEntity() }
        mealCategoryDao.insertAll(content)
        return mealCategoryDao.getAllMealCategories()
    }
}