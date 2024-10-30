package com.example.laboratorio7_retrofit

import android.app.Application
import androidx.room.Room
import com.example.laboratorio7_retrofit.database.AppDatabase
import com.example.laboratorio7_retrofit.networking.MealsWebService
import com.example.laboratorio7_retrofit.ui.categories.Repository.MealsCategoryRepository
import com.example.laboratorio7_retrofit.ui.market.repository.SuperMarketRepository

class MyApp : Application() {

    // Singleton instance of the Room database
    private lateinit var database: AppDatabase
        private set

    lateinit var categoryRepository: MealsCategoryRepository
        private set

    lateinit var supermarketRepository: SuperMarketRepository
        private set

    lateinit var categoryWebService: MealsWebService
        private set

    override fun onCreate() {
        super.onCreate()

        // Initialize the Meals web service
        categoryWebService = MealsWebService()

        // Initialize Room database
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "meal-categories-db"
        ).build()

        // Initialize repositories with corresponding DAOs
        categoryRepository = MealsCategoryRepository(
            categoryWebService,
            database.mealCategoryDao()
        )

        supermarketRepository = SuperMarketRepository(
            database.supermarketItemDao()
        )
    }
}
