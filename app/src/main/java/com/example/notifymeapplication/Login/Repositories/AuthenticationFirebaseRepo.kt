package com.example.notifymeapplication.Login.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.notifymeapplication.Login.Model.RegisterUserDataClass

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class AuthenticationFirebaseRepo() {
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseDatabase
    var firebaseUserLogin : MutableLiveData<FirebaseUser> = MutableLiveData()
    var firebaseRegisterUser  : MutableLiveData<FirebaseUser> = MutableLiveData()

    fun  registerUserToFirebase(email :String , password : String){
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email , password).addOnCompleteListener{
            if (it.isSuccessful){
                firebaseRegisterUser.value = auth.currentUser
            }else{
                Log.d("authRepo" , auth.currentUser.toString())
            }
        }
    }

    fun addUserInDatabase(email : String ,userName :String ,phoneNumber: String ){
        val userObj = RegisterUserDataClass(auth.currentUser?.uid.toString() , userName  , email  , phoneNumber)
        db = FirebaseDatabase.getInstance()
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(auth.currentUser?.uid.toString()).setValue(userObj)
        Log.d("AuthRepo" , "addUserInDatabase , $userObj")
    }


    fun loginUserToFirebase(email: String , password: String){
        auth =  FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email , password).addOnCompleteListener {
            if(it.isSuccessful){
                firebaseUserLogin.value = auth.currentUser
            }else{
                Log.d("authRepo" , auth.currentUser.toString())
            }
        }
    }
}



