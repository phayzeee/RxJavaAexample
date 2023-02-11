package com.example.rxjavaaexample.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rxjavaaexample.data.repository.Repository
import javax.inject.Inject

class ViewModelFactory (private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
