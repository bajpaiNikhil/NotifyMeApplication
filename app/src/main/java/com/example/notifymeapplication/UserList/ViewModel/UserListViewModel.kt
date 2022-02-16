package com.example.notifymeapplication.UserList.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notifymeapplication.Login.Model.RegisterUserDataClass
import com.example.notifymeapplication.UserList.Model.MessageDataClass
import com.example.notifymeapplication.UserList.Repository.UsersListRepo
import kotlinx.coroutines.launch

class UserListViewModel(private val repo : UsersListRepo): ViewModel() {

    var userList : MutableLiveData<RegisterUserDataClass> = MutableLiveData()
    var chatList : MutableLiveData<MessageDataClass> = MutableLiveData()

    fun getUserList(){
        viewModelScope.launch {
            repo.getUserList()
            userList = repo.userList
        }
    }

    fun readMessage(senderId : String){
        viewModelScope.launch {
            repo.checkForMessage(senderId )
            chatList = repo.chatList
        }
    }


}