package de.syntax_institut.androidabschlussprojekt.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirebaseAuthService {

    private val auth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<FirebaseUser?>(null)
    val authState = _authState.asStateFlow()

    init {
        auth.addAuthStateListener { newAuthState ->
            _authState.value = newAuthState.currentUser
        }
    }

    fun registerUserWithEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    fun loginUserWithEmail(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    fun logoutUser() {
        auth.signOut()
    }
}
