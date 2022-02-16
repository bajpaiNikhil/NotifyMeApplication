package com.example.notifymeapplication.Notify.ViewModel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notifymeapplication.Notify.Repository.NotificationRepo
import kotlinx.coroutines.launch

class NotifyViewModel(private val repo : NotificationRepo) : ViewModel() {

    fun deleteMessage(){
        viewModelScope.launch {  }
        repo.removeNotification()
    }

}