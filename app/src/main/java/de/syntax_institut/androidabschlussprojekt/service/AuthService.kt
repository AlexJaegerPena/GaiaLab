package de.syntax_institut.androidabschlussprojekt.service

import android.R.attr.password
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class AuthService {

    private val auth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<FirebaseUser?>(null)
    val authState = _authState.asStateFlow()

    init {
        auth.addAuthStateListener { newAuthState ->
            _authState.value = newAuthState.currentUser
        }
    }

    suspend fun registerUserWithEmail(email: String, password: String): FirebaseUser? {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user
    }

    suspend fun loginUserWithEmail(email: String, password: String): FirebaseUser? {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user
    }

    fun logoutUser() {
        auth.signOut()
    }
}
