package com.example.homeworkstbc.presentation.screen.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homeworkstbc.databinding.FragmentSplashBinding
import com.example.homeworkstbc.presentation.common.BaseFragment
import com.example.homeworkstbc.presentation.extension.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels()

    override fun bind() {
        observe()
        viewModel.onEvent(SplashEvent.OnStartSplash)
    }

    private fun observe() {
        collectFlow(viewModel.sideEffect) { effect ->
            when(effect) {
                SplashSideEffect.NavigateToAuth -> navigateToAuth()
                SplashSideEffect.NavigateToHome -> navigateToHome()
            }
        }
    }

    private fun navigateToAuth() {
        val action = SplashFragmentDirections.actionSplashFragmentToAuthFragment()
        findNavController().navigate(action)
    }

    private fun navigateToHome() {
        val action = SplashFragmentDirections.actionSplashFragmentToMainFlow()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onEvent(SplashEvent.OnStopSplash)
    }
}