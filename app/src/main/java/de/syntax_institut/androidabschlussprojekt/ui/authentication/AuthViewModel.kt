package de.syntax_institut.androidabschlussprojekt.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.User
import de.syntax_institut.androidabschlussprojekt.service.AuthService
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.UserViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(val authService: AuthService) : ViewModel() {

    val currentUser: StateFlow<FirebaseUser?> = authService.authState

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error =_error.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()


    fun registerAndSaveUser(userVM: UserViewModel) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                authService.registerUserWithEmail(email.value, password.value)
                val firebaseUser = currentUser.value
                firebaseUser?.let  {
                    val newUser = User(
                        userId = it.uid,
                        username = "",
                        email = it.email
                    )
                    userVM.saveUser(newUser)
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unbekannter Fehler"
            } finally {
                _loading.value = false
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                authService.loginUserWithEmail(email.value, password.value)
            } catch (e: Exception) {
                _error.value = e.message ?: "Unbekannter Fehler"
            } finally {
                _loading.value = false
            }
        }
    }

    fun logout() {
        authService.logoutUser()
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        clearError()
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        clearError()
    }

    fun clearError() {
        _error.value = null
    }
}