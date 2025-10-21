package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavFactRepository
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavFactViewModel(
    private val repo: FavFactRepository,
    private val userVM: UserViewModel
): ViewModel() {

    private var userId: String? = null

    val favFacts = repo.favFacts

    init {
        viewModelScope.launch {
            userVM.currentUser.collect { user ->
                userId = user?.userId
                if (userId != null) {
                    repo.listenToFavorites(userId!!)
                } else {
                    Log.e("FavFactViewModel", "Kein User angemeldet")
                }
            }
        }
    }

    fun addFavoriteFact(factId: Int) {
        val uid = userId
        if (uid == null) {
            Log.e("FavFactViewModel", "Kein User angemeldet. FavFact kann nicht gespeichert werden.")
            return
        }
        viewModelScope.launch {
            try {
                repo.addFavoriteFact(uid, factId)
            } catch (e: Exception) {
                Log.e("FavFactViewModel", "Fehler beim Hinzufügen des FavFacts: ${e.toString()}")
            }
        }
    }

    fun removeFavoriteFact(factId: Int) {
        val uid = userId
        if (uid == null) {
            Log.e("FavFactViewModel", "Kein User angemeldet. FavFact kann nicht gelöscht werden.")
            return
        }
        repo.removeFavoriteFact(uid, factId)
    }
}