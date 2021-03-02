package com.creativedrewy.restaurantfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.creativedrewy.restaurantfinder.databinding.FragmentLoginBinding
import com.creativedrewy.restaurantfinder.viewmodel.AlreadyLoggedIn
import com.creativedrewy.restaurantfinder.viewmodel.LoginFailure
import com.creativedrewy.restaurantfinder.viewmodel.LoginSuccess
import com.creativedrewy.restaurantfinder.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var viewBinding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentLoginBinding.inflate(inflater, container, false)

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.loginButton.setOnClickListener {
            viewModel.loginToDoorDash(
                viewBinding.emailInputTextview.text.toString(),
                viewBinding.passwordInputTextview.text.toString()
            )
        }

        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                AlreadyLoggedIn -> {
                    navigateToListFragment()
                }
                LoginSuccess -> {
                    navigateToListFragment()
                }
                LoginFailure -> {

                }
            }
        }

        viewModel.checkIsLoggedIn()
    }

    private fun navigateToListFragment() {
        viewBinding.root.findNavController()
            .navigate(LoginFragmentDirections.actionLoginFragment2ToRestaurantListFragment())
    }
}