package com.example.homeworkstbc.presentation.screen.register

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homeworkstbc.R
import com.example.homeworkstbc.databinding.FragmentRegisterBinding
import com.example.homeworkstbc.domain.validator.SignUpValidationEnum
import com.example.homeworkstbc.presentation.common.BaseFragment
import com.example.homeworkstbc.presentation.extension.collectFlow
import com.example.homeworkstbc.presentation.extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel: RegisterViewModel by viewModels()
    override fun bind() {
        observe()
    }

    override fun listeners() = with(binding) {
        btnNavToLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val repeatPassword = etPasswordConfirmation.text.toString()

            viewModel.onEvent(RegisterEvent.RegisterUser(email, password, repeatPassword))
        }
    }

    private fun observe() = with(binding) {
        collectFlow(viewModel.sideEffect) { sideEffect ->
            when (sideEffect) {
                is RegisterSideEffect.NavigateToHome -> {
                    val action = RegisterFragmentDirections.actionRegisterFragmentToMainFlow()
                    findNavController().navigate(action)
                }

                is RegisterSideEffect.SendError -> {
                    val errorMessage = when (sideEffect.message) {
                        SignUpValidationEnum.EMPTY_FIELDS -> getString(R.string.fill_all_fields)
                        SignUpValidationEnum.INCORRECT_EMAIL -> getString(R.string.incorrect_email)
                        SignUpValidationEnum.PASSWORD_DO_NOT_MATCH -> getString(R.string.passwords_do_not_match)
                        SignUpValidationEnum.PASSWORD_TOO_SHORT -> getString(R.string.password_should_be_at_least_6_characters)

                        "ERROR_EMAIL_ALREADY_IN_USE" -> getString(R.string.this_email_is_already_in_use)

                        else -> getString(R.string.unknown_error_occurred)
                    }
                    root.showSnackBar(errorMessage)
                }
            }
        }
    }
}
