package com.elihimas.accountant.model.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.elihimas.accountant.model.CountEntry

@Dao
interface AccountDao {

    @Query("SELECT * from CountEntry")
    fun allEntries(): LiveData<List<CountEntry>>

    @Insert
    fun addEntry(countEntry: CountEntry)

    @Query("DELETE FROM CountEntry")
    fun clear()

    @Delete
    fun delete(countEntry: CountEntry)

}
