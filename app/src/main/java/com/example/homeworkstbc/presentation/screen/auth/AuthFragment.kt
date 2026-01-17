package com.example.homeworkstbc.presentation.screen.auth

import androidx.navigation.fragment.findNavController
import com.example.homeworkstbc.databinding.FragmentAuthBinding
import com.example.homeworkstbc.presentation.common.BaseFragment


class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {
    override fun bind() {
    }

    override fun listeners() = with(binding){
        btnLogIn.setOnClickListener {
            navigateToLogin()
        }
        btnNewAcc.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun navigateToLogin() {
        val action = AuthFragmentDirections.actionAuthFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun navigateToRegister() {
        val action = AuthFragmentDirections.actionAuthFragmentToRegisterFragment()
        findNavController().navigate(action)

    }
}