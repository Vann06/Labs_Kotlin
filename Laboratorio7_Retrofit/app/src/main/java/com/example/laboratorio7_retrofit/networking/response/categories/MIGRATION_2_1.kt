package com.example.laboratorio7_retrofit.networking.response.categories

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

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE users "
                    + "ADD COLUMN address TEXT"
        )
    }
}

@Database(entities = [MealCategoryEntity::class, SupermarketItemEntity::class], version = 2)
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
                )
                    .addMigrations(MIGRATION_1_2) // Add migration
                    // Uncomment this line to allow for destructive migrations
                    // .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}