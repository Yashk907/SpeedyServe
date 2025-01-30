package com.example.speedyserve.frontend.SignUpAndSignIn

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedyserve.Backend.Authentication.Models.User
import com.example.speedyserve.Backend.Authentication.Repo.AuthRepo
import com.example.speedyserve.Backend.Authentication.Repo.AuthRepoImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewmodel @Inject constructor (private val repo: AuthRepoImp): ViewModel(){

    private val _state = mutableStateOf(AuthState())
    val state: AuthState get() = _state.value


    fun onEvent(event: AuthUiEvent) {
        when (event) {
            AuthUiEvent.SignIn -> signIn()
            is AuthUiEvent.SignInPasswordChanged -> {
                _state.value = _state.value.copy(signInPassword = event.value)
            }
            is AuthUiEvent.SignInUsernameChanged -> {
                _state.value = _state.value.copy(signInUsername = event.value)
            }
            AuthUiEvent.SignUp -> signUp()
            is AuthUiEvent.SignUpPasswordChanged -> {
                _state.value = _state.value.copy(signUpPassword = event.value)
            }
            is AuthUiEvent.SignUpUsernameChanged -> {
                _state.value = _state.value.copy(signUpUsername = event.value)
            }

            is AuthUiEvent.SignUpEmail -> {
                _state.value=_state.value.copy(
                    email = event.value
                )
            }
            is AuthUiEvent.SignUpMobileNum -> {
                _state.value=_state.value.copy(
                    phoneNumber = event.value
                )
            }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            repo.signIn(
                User(
                    username =_state.value.signInUsername,
                    password = _state.value.signInPassword,
//                    email = _state.value.email,
//                    mobileNo = _state.value.phoneNumber
                )
            )
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            repo.signUp(
                User(
                    username =_state.value.signUpUsername,
                    password = _state.value.signUpPassword,
                    email = _state.value.email,
                    mobile = _state.value.phoneNumber
                )
            )
            _state.value = _state.value.copy(isLoading = false)
        }
    }


}