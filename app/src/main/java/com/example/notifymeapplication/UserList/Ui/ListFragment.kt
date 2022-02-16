package com.example.notifymeapplication.UserList.Ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notifymeapplication.Login.Model.RegisterUserDataClass
import com.example.notifymeapplication.R
import com.example.notifymeapplication.UserList.Adapter.UserListAdapter
import com.example.notifymeapplication.UserList.Repository.UsersListRepo
import com.example.notifymeapplication.UserList.ViewModel.UserListViewModel
import com.example.notifymeapplication.UserList.ViewModel.UserListViewModelFactory
import com.example.notifymeapplication.databinding.FragmentListBinding
import com.google.firebase.auth.FirebaseAuth

class ListFragment : Fragment() {

    lateinit var senderId  : String
    lateinit var auth : FirebaseAuth
    private  var _binding : FragmentListBinding?=null
    private val binding get() = _binding!!

    private lateinit var listFragmentViewModel: UserListViewModel

    private lateinit var userArrayList : ArrayList<RegisterUserDataClass>



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

        userArrayList = ArrayList()

        auth = FirebaseAuth.getInstance()

        senderId = auth.currentUser?.uid.toString()

        setHasOptionsMenu(true)

        binding.userListRview.layoutManager = LinearLayoutManager(context)
        listFragmentViewModel = ViewModelProvider(this , UserListViewModelFactory(UsersListRepo()))[UserListViewModel::class.java]

        listFragmentViewModel.getUserList()
        listFragmentViewModel.userList.observe(viewLifecycleOwner , Observer {
            Log.d("ListFragment" , "user in the application $it")
            userArrayList.add(it)
            binding.userListRview.adapter = UserListAdapter(userArrayList)
        })


        listFragmentViewModel.readMessage(senderId)
        listFragmentViewModel.chatList.observe(viewLifecycleOwner , Observer {
            Log.d("ListFragment" , "chat we saw $it")

            findNavController().navigate(R.id.notifyFragment)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu , menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logOut->{
                auth.signOut()
                findNavController().navigate(R.id.loginFragment)
                Log.d("ListFragment" , "Menu item clicked")

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}