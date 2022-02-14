package com.example.notifymeapplication.Login.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notifymeapplication.Login.Repositories.AuthenticationFirebaseRepo

class AuthFirebaseViewModelFactory(val repo: AuthenticationFirebaseRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthFirebaseViewModel(repo) as T
    }
}