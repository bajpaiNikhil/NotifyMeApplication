package com.example.notifymeapplication.Notify.Ui

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.MessageQueue
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notifymeapplication.Notify.Repository.NotificationRepo
import com.example.notifymeapplication.Notify.ViewModel.NotifyViewModel
import com.example.notifymeapplication.Notify.ViewModel.NotifyViewModelFactory
import com.example.notifymeapplication.R
import com.example.notifymeapplication.UserList.Model.MessageDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class NotifyFragment : Fragment() {

    lateinit var auth : FirebaseAuth

    lateinit var player: MediaPlayer

    lateinit var notifyViewModel : NotifyViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notifyViewModel = ViewModelProvider(this ,NotifyViewModelFactory(NotificationRepo())).get(NotifyViewModel::class.java)

        auth = FirebaseAuth.getInstance()

        val handler = Handler()
        playNotification()
        handler.postDelayed(object :Runnable{
            override fun run() {
                muteNotification()
                notifyViewModel.deleteMessage()
                findNavController().navigate(R.id.listFragment)
            }
        },10000)
    }

    private fun playNotification() {
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        player = MediaPlayer.create(requireContext(), notification)
        player.isLooping = true
        Log.d("NotifyFragment" , "Player is Playing")
        player.start()
    }
    private fun muteNotification() {
        Log.d("NotifyFragment" , "Player is Stopped")
        player.stop()
    }


}