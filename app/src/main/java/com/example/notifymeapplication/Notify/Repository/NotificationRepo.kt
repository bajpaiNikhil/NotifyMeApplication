package com.example.notifymeapplication.Notify.Repository

import com.example.notifymeapplication.UserList.Model.MessageDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NotificationRepo {
    lateinit var auth : FirebaseAuth
    fun removeNotification(){
        val ref  = FirebaseDatabase.getInstance().getReference("Chat")
        ref.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(deleteSnapshot in snapshot.children){
                        val deleteObj = deleteSnapshot.getValue(MessageDataClass::class.java)
                        if(deleteObj?.receiverId.equals(auth.currentUser?.uid.toString())){
                            val deleteRef = FirebaseDatabase.getInstance().getReference("Chat").child(deleteSnapshot.key.toString())
                            deleteRef.removeValue()
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}