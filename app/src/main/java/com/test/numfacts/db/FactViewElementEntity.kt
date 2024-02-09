package com.test.numfacts.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fact_view_elements")
data class FactViewElementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "fact")
    val fact: String
)