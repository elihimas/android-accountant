package com.elihimas.accountant.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elihimas.accountant.model.repository.CountRepository

class AccountantViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountViewModel(CountRepository(context)) as T
    }

}
