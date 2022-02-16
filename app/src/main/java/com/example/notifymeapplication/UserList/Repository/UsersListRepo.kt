package com.example.notifymeapplication.UserList.Repository

import android.os.Message
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.notifymeapplication.Login.Model.RegisterUserDataClass
import com.example.notifymeapplication.UserList.Model.MessageDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsersListRepo {
     private lateinit var auth : FirebaseAuth
     val userList : MutableLiveData<RegisterUserDataClass> = MutableLiveData()
     val chatList : MutableLiveData<MessageDataClass> = MutableLiveData()


     fun getUserList(){
         auth = FirebaseAuth.getInstance()
         val ref = FirebaseDatabase.getInstance().getReference("Users")
         ref.addValueEventListener( object : ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 if( snapshot.exists()){
                     for(userSnapshot in snapshot.children){
                         val userIs =  userSnapshot.getValue(RegisterUserDataClass::class.java)
                         if(userIs?.userId.toString() != auth.currentUser?.uid.toString()){
                             Log.d("UserListRepo" , "${userIs?.userId.toString()} ,${auth.currentUser?.uid.toString()}")
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
    fun checkForMessage(senderId : String){
        val ref =  FirebaseDatabase.getInstance().getReference("Chat")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if( snapshot.exists()){
                    for(chatSnapshot in snapshot.children){
                        val chatObj  = chatSnapshot.getValue(MessageDataClass::class.java)
                        Log.d("UserListRepo" , "chat in the application ${chatObj?.senderId} , ${chatObj?.receiverId} , $senderId ")
                        if(chatObj?.receiverId.equals(senderId)){
                            //move to the ring fragment
                            chatList.value = chatObj!!
                            Log.d("UserListRepo" , "chat in the application $chatObj")
                        }
                    }
                    Log.d("UserListRepo" , "chat list $chatList")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}