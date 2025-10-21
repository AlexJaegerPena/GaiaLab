package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.User
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.UserRepository
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import kotlinx.coroutines.launch

class UserViewModel(
    private val repo: UserRepository,
    private val authVM: AuthViewModel
): ViewModel() {

    private var userId: String? = null

    init {
        viewModelScope.launch {
            authVM.currentUser.collect { user ->
                userId = user?.uid
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

    fun deleteUser(user: User) {
        viewModelScope.launch {
            try {
                repo.deleteUser(user)
            } catch (e: Exception){
                Log.e("UserViewModel", "Fehler beim Löschen des Users: ${e.toString()}")
            }
        }
    }
}