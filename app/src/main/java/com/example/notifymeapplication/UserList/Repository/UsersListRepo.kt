package com.example.notifymeapplication.UserList.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.notifymeapplication.Login.Model.RegisterUserDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsersListRepo {
     private lateinit var auth : FirebaseAuth
     private lateinit var db : FirebaseDatabase

     val userList : MutableLiveData<RegisterUserDataClass> = MutableLiveData()

     fun getUserList(){
         auth = FirebaseAuth.getInstance()

         val ref = FirebaseDatabase.getInstance().getReference("Users")

         ref.addValueEventListener( object : ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 if( snapshot.exists()){
                     for(userSnapshot in snapshot.children){
                         val userIs =  userSnapshot.getValue(RegisterUserDataClass::class.java)
                         if(userIs?.userId != auth.currentUser?.uid){
                             userList.value = userIs!!
                         }
                     }
                     Log.d("UserListRepo" , "user in Application $userList")
                 }
             }

             override fun onCancelled(error: DatabaseError) {
                 TODO("Not yet implemented")
             }

         })
     }
}