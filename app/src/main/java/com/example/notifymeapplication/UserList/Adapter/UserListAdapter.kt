package com.example.notifymeapplication.UserList.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notifymeapplication.Login.Model.RegisterUserDataClass
import com.example.notifymeapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserListAdapter(private val userArrayList : ArrayList<RegisterUserDataClass>) : RecyclerView.Adapter<UserListAdapter.UserHolder>() {
    lateinit var auth : FirebaseAuth
    var selectedUser = userArrayList[0].userId
    inner class UserHolder(view : View): RecyclerView.ViewHolder(view){
        val userName: TextView = view.findViewById<TextView>(R.id.userNameTV)
        val notifyBtn: ImageButton = view.findViewById<ImageButton>(R.id.userNotifyBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context!!).inflate(R.layout.user_list_item , parent , false)
        return UserHolder(view)

    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val currentItem = userArrayList[position]
        auth = FirebaseAuth.getInstance()

        holder.userName.text = currentItem.userName.toString()
        holder.notifyBtn.setOnClickListener {
            val hashMap : HashMap<String , String> = HashMap()
            if (selectedUser?.isEmpty() == true){
                selectedUser = currentItem.userId.toString()
            }else{
                selectedUser = currentItem.userId.toString()
            }
            hashMap["senderId"] = auth.currentUser?.uid.toString()
            hashMap["receiverId"] = currentItem.userId.toString()

            hashMap["message"] = "IamStupid"

            val ref = FirebaseDatabase.getInstance().getReference("Chat").push().setValue(hashMap)

            Toast.makeText( it.context, "button pressed" , Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }
}