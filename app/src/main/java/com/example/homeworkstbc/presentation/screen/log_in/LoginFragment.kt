package com.example.homeworkstbc.presentation.screen.log_in

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homeworkstbc.R
import com.example.homeworkstbc.databinding.FragmentLoginBinding
import com.example.homeworkstbc.domain.validator.LoginValidationEnum
import com.example.homeworkstbc.presentation.common.BaseFragment
import com.example.homeworkstbc.presentation.extension.collectFlow
import com.example.homeworkstbc.presentation.extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()

    override fun bind() {
        observe()

    }

    override fun listeners() = with(binding) {
        navToRegister()
        logInListener()
    }

    private fun navToRegister() = with(binding) {
        btnNavToRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    private fun logInListener() = with(binding) {
        btnSignIn.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            viewModel.onEvent(LoginEvent.LoginUser(email = email, password = password))
        }
    }

    private fun navigateToHome() {
        val action = LoginFragmentDirections.actionLoginFragmentToMainFlow()
        findNavController().navigate(action)
    }

    private fun observe() = with(binding) {
        collectFlow(viewModel.state) {
            binding.progressBar.isVisible = it.loader
        }

        collectFlow(viewModel.sideEffect) { sideEffect ->
            when (sideEffect) {
                is LoginSideEffect.NavigateToHome -> navigateToHome()
                is LoginSideEffect.ShowError -> {
                    val errorMessage = when (sideEffect.message) {
                        LoginValidationEnum.EMPTY_FIELDS -> getString(R.string.fill_all_fields)
                        LoginValidationEnum.INCORRECT_EMAIL -> getString(R.string.incorrect_email)

                        "ERROR_USER_NOT_FOUND", "ERROR_WRONG_PASSWORD", "ERROR_INVALID_CREDENTIAL" ->
                            getString(R.string.invalid_email_or_password)

                        else -> getString(R.string.unknown_error_occurred)
                    }
                    root.showSnackBar(errorMessage)
                }
            }
        }
    }
}