package com.example.notifymeapplication.Notify.ViewModel


import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notifymeapplication.Notify.Repository.NotificationRepo
import kotlinx.coroutines.launch

class NotifyViewModel(private val repo : NotificationRepo) : ViewModel() {
    lateinit var player:MediaPlayer

    fun deleteMessage(){
        viewModelScope.launch {  }
        repo.removeNotification()
    }

    fun muteNotification() {
        Log.d("NotifyFragment" , "Player is Stopped")
        player.stop()
    }
}