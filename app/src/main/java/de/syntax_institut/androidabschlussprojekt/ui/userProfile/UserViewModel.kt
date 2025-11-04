package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.User
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.UserRepository
import de.syntax_institut.androidabschlussprojekt.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val repo: UserRepository,
    private val authService: AuthService
): ViewModel() {


    private var userId: String? = null

    val username = repo.currentUser.map { it?.username ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    init {
        viewModelScope.launch {
            authService.authState.collect { firebaseUser ->
                userId = firebaseUser?.uid
                if (userId != null) {
                    repo.listenToCurrentUser(userId!!)
                } else {
                    Log.e("UserViewModel", "Kein User angemeldet.")
                }
            }
        }
    }

    fun saveUser(newUser: User) {
        viewModelScope.launch {
            try {
                repo.createUser(newUser)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Fehler beim Speichern des neuen Users: ${e.toString()}")
            }
        }
    }

    fun updateUsername(newName: String) {
        val uid = userId
        if (uid == null) {
            Log.e("UserViewModel", "Kein User angemeldet")
            return
        }
        viewModelScope.launch {
            try {
                repo.updateUsername(uid, newName)
                Log.d("UserViewModel", "Username gespeichert: $newName")
            } catch (e: Exception) {
                Log.e("UserViewModel", "Fehler beim Update: ${e.toString()}")
            }
        }
    }

    fun deleteUser(user: User) {
        val uid = userId
        if (uid == null) {
            Log.e("UserViewModel", "Kein User angemeldet")
            return
        }
        viewModelScope.launch {
            try {
                repo.deleteUser(user)
            } catch (e: Exception){
                Log.e("UserViewModel", "Fehler beim Löschen des Users: ${e.toString()}")
            }
        }
    }
}