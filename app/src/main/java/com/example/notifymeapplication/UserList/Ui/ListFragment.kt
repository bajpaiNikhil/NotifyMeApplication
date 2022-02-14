package com.example.notifymeapplication.UserList.Ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.notifymeapplication.R
import com.example.notifymeapplication.UserList.Repository.UsersListRepo
import com.example.notifymeapplication.UserList.ViewModel.UserListViewModel
import com.example.notifymeapplication.UserList.ViewModel.UserListViewModelFactory
import com.example.notifymeapplication.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private  var _binding : FragmentListBinding?=null
    private val binding get() = _binding!!

    private lateinit var listFragmentViewModel: UserListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFragmentViewModel = ViewModelProvider(this , UserListViewModelFactory(UsersListRepo())).get(UserListViewModel::class.java)

        listFragmentViewModel.getUserList()
        listFragmentViewModel.userList.observe(viewLifecycleOwner , Observer {
            Log.d("ListFragment" , "user in the application $it")
        })
    }










    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}