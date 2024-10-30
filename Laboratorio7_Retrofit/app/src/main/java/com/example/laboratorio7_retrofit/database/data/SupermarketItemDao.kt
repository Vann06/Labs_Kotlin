package com.example.laboratorio7_retrofit.database.data

import androidx.room.*

@Dao
interface SupermarketItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: SupermarketItemEntity)

    @Update
    suspend fun updateItem(item: SupermarketItemEntity)

    @Delete
    suspend fun deleteItem(item: SupermarketItemEntity)

    @Query("SELECT * FROM supermarket_items")
    suspend fun getAllItems(): List<SupermarketItemEntity>

    @Query("SELECT * FROM supermarket_items WHERE id = :itemId")
    suspend fun getItemById(itemId: Int): SupermarketItemEntity?
}

