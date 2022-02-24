package com.elihimas.accountant.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elihimas.accountant.model.CountEntry

@Database(entities = [CountEntry::class], version = 1)
abstract class CountyDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}
