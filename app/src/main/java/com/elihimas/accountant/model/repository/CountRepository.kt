package com.elihimas.accountant.model.repository

import android.content.Context
import androidx.room.Room
import com.elihimas.accountant.model.CountEntry

class CountRepository(context: Context) {

    private val db = Room.databaseBuilder(
        context,
        CountyDatabase::class.java, "database-name"
    ).build()

    fun allEntries() = db.accountDao().allEntries()

    fun addEntry(countEntry: CountEntry) =
        db.accountDao().addEntry(countEntry)

    fun delete(countEntry: CountEntry)  = db.accountDao().delete(countEntry)

    fun clear() = db.accountDao().clear()
}
