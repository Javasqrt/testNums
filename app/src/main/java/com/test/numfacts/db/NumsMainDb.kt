package com.test.numfacts.db

import android.app.Activity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FactViewElementEntity::class], version = 1)
abstract class NumsMainDb : RoomDatabase() {
    abstract fun getDao(): FactViewElementDao

    companion object {
        fun getDb(activity: Activity): NumsMainDb {
            return Room.databaseBuilder(
                activity.applicationContext,
                NumsMainDb::class.java,
                "db"
            )
                .build()
        }
    }
}