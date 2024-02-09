package com.test.numfacts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FactViewElementDao {
    @Insert
    fun insert(list: FactViewElementEntity)

    @Query("SELECT * FROM fact_view_elements")
    fun getAllElements(): Flow<List<FactViewElementEntity>>
}