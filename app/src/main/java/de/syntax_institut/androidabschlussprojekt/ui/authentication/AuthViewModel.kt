package de.syntax_institut.androidabschlussprojekt.ui.authentication

import androidx.lifecycle.ViewModel
import de.syntax_institut.androidabschlussprojekt.service.FirebaseAuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel(
    val authService: FirebaseAuthService
): ViewModel() {

    val fireUser = authService.authState

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun register() {
        authService.registerUserWithEmail(email.value, password.value)
    }

    fun login() {
        authService.loginUserWithEmail(email.value, password.value)
    }

    fun logout() {
        authService.logoutUser()
    }

    fun onEmailInput(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordInput(newPassword: String) {
        _password.value = newPassword
    }

}