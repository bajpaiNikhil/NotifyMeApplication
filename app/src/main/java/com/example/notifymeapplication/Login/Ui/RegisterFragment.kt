package com.example.notifymeapplication.Login.Ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notifymeapplication.Login.Repositories.AuthenticationFirebaseRepo
import com.example.notifymeapplication.Login.ViewModel.AuthFirebaseViewModel
import com.example.notifymeapplication.Login.ViewModel.AuthFirebaseViewModelFactory
import com.example.notifymeapplication.R
import com.example.notifymeapplication.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    lateinit var registerViewModel: AuthFirebaseViewModel
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerViewModel =
            ViewModelProvider(this, AuthFirebaseViewModelFactory(AuthenticationFirebaseRepo()))
                .get(AuthFirebaseViewModel::class.java)
        navigate()
    }

    private fun navigate() {
        binding.firebaseRegisterLoginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.firebaseRRegisterBtn.setOnClickListener {
            val email = binding.firebaseRegisterEmailTv.text.toString()
            val password = binding.firebaseRegisterPasswordTv.text.toString()
            val userName = binding.firebaseRegisterUsernameTv.text.toString()
            val phoneNumber = binding.firebaseRegisterPhNumberTV.text.toString()
            registerViewModel.registerUser(email, password)
            addUser()
            registerViewModel.registerUserInDatabase(email, userName, phoneNumber)
            registerViewModel.registerUserData.observe(viewLifecycleOwner, Observer {
                Log.d("registerFragment", "User Register")
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            })

        }
    }

    private fun addUser() {
        val email = binding.firebaseRegisterEmailTv.text.toString()
        val userName = binding.firebaseRegisterUsernameTv.text.toString()
        val phoneNumber = binding.firebaseRegisterPhNumberTV.text.toString()
        registerViewModel.registerUserInDatabase(email, userName, phoneNumber)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}