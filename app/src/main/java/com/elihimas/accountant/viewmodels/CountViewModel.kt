package com.elihimas.accountant.viewmodels

import androidx.lifecycle.*
import com.elihimas.accountant.model.CountEntry
import com.elihimas.accountant.model.repository.CountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountViewModel(private val countRepository: CountRepository) : ViewModel() {

    val countEntries = countRepository.allEntries()
    val count = Transformations.map(countEntries) { it.sumOf(CountEntry::quantity) }

    fun consolidateQuantity(quantity: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            countRepository.clear()
            doAddEntry(quantity)
        }
    }

    fun addEntry(entryQuantity: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            doAddEntry(entryQuantity)
        }
    }

    private fun doAddEntry(entryQuantity: Int) {
        val countEntry = CountEntry(entryQuantity)
        countRepository.addEntry(countEntry)
    }

    fun deleteEntry(countEntry: CountEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            countRepository.delete(countEntry)
        }
    }
}
