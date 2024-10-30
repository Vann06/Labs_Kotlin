package com.example.laboratorio7_retrofit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.laboratorio7_retrofit.database.categories.MealCategoryDao
import com.example.laboratorio7_retrofit.database.categories.MealCategoryEntity
import com.example.laboratorio7_retrofit.database.data.SupermarketItemDao
import com.example.laboratorio7_retrofit.database.data.SupermarketItemEntity

@Database(entities = [MealCategoryEntity::class, SupermarketItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealCategoryDao(): MealCategoryDao
    abstract fun supermarketItemDao(): SupermarketItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

