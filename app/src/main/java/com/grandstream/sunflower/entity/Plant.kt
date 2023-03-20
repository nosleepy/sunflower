package com.grandstream.sunflower.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class Plant(
    val name: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val amount: String = "",
    val level: String = "",
    val price: String = "",
    val flag: String = "0",
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val plantId: Int = 0) {
}