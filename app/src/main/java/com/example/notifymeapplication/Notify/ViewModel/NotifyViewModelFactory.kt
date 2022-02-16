package com.example.notifymeapplication.Notify.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notifymeapplication.Notify.Repository.NotificationRepo

class NotifyViewModelFactory(private val repo : NotificationRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotifyViewModel(repo) as T
    }

}