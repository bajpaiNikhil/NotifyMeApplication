package com.example.notifymeapplication.UserList.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notifymeapplication.UserList.Repository.UsersListRepo

class UserListViewModelFactory(private var repo : UsersListRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(repo) as T

    }

}