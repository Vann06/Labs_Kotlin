package com.example.laboratorio7_retrofit.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supermarket_items")
data class SupermarketItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "item_name")
    val itemName: String,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "image_path")
    val imagePath: String? = null // Ruta opcional para la imagen del artículo
)
