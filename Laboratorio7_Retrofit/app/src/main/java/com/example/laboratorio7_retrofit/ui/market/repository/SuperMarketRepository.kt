package com.example.laboratorio7_retrofit.ui.market.repository

import com.example.laboratorio7_retrofit.database.data.SupermarketItemDao
import com.example.laboratorio7_retrofit.database.data.SupermarketItemEntity

public class SuperMarketRepository(private val dao: SupermarketItemDao) {

    suspend fun getItemById(itemId: Int): SupermarketItemEntity? {
        return dao.getItemById(itemId)
    }

    suspend fun getAllItems(): List<SupermarketItemEntity> {
        return dao.getAllItems()
    }

    suspend fun insertItem(item: SupermarketItemEntity) {
        dao.insertItem(item)
    }

    suspend fun updateItem(item: SupermarketItemEntity) {
        dao.updateItem(item)
    }

    suspend fun deleteItem(item: SupermarketItemEntity) {
        dao.deleteItem(item)
    }
}