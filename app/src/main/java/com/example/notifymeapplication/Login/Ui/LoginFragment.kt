package com.example.notifymeapplication.Login.Ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notifymeapplication.Login.Repositories.AuthenticationFirebaseRepo
import com.example.notifymeapplication.Login.ViewModel.AuthFirebaseViewModel
import com.example.notifymeapplication.Login.ViewModel.AuthFirebaseViewModelFactory
import com.example.notifymeapplication.R
import com.example.notifymeapplication.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var loginViewMode : AuthFirebaseViewModel
    private var _binding : FragmentLoginBinding?= null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewMode = ViewModelProvider(this , AuthFirebaseViewModelFactory(AuthenticationFirebaseRepo())).get(AuthFirebaseViewModel::class.java)

        navigateTo()
    }

    private fun navigateTo() {
        binding.fireBaseRegisterBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.firebaseLoginBtn.setOnClickListener {
            val email = binding.firebaseLoginEmailTv.text.toString()
            val password = binding.firebaseLoginPasswordTv.text.toString()
            loginViewMode.loginUser(email , password)
            loginViewMode.loginUserData.observe(viewLifecycleOwner , Observer {
               Log.d("loginFragment" , "LoginAccess")
                findNavController().navigate(R.id.action_loginFragment_to_listFragment)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}