package com.example.notifymeapplication.Login.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notifymeapplication.Login.Repositories.AuthenticationFirebaseRepo
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch


class AuthFirebaseViewModel(private var repository : AuthenticationFirebaseRepo): ViewModel(){

    var loginUserData  : MutableLiveData<FirebaseUser>  = MutableLiveData()
    var registerUserData : MutableLiveData<FirebaseUser>  = MutableLiveData()

    fun loginUser(email : String , password:String){
        viewModelScope.launch {
            repository.loginUserToFirebase(email , password)
            loginUserData = repository.firebaseUserLogin
        }
    }
    fun registerUser(email: String ,password: String , userName: String , phoneNumber: String){
        viewModelScope.launch {
            repository.registerUserToFirebase(email , password , userName  ,phoneNumber)
            registerUserData = repository.firebaseRegisterUser
            Log.d("authViewModel" , registerUserData.toString())
        }
    }

//    fun registerUserInDatabase(email: String , userName : String , phoneNumber: String){
//        viewModelScope.launch {
//            repository.addUserInDatabase(email , userName , phoneNumber)
//        }
//    }


}