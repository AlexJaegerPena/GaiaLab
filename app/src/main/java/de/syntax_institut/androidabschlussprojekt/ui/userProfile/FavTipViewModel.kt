package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavTipRepository
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavTipViewModel(
    private val repo: FavTipRepository,
    private val authVM: AuthViewModel
    ): ViewModel() {

    private var userId: String? = null

    val favTips = repo.favTips

    init {
        viewModelScope.launch {
            authVM.currentUser.collect { user ->
                userId = user?.uid
                if (userId != null) {
                    repo.listenToFavorites(userId!!)
                } else {
                    Log.e("FavTipViewModel", "Kein User angemeldet.")
                }
            }
        }
    }

    fun addFavoriteTip(tip: Tip) {
        val uid = userId
        if (uid == null) {
            Log.e("FavTipViewModel", "Kein User angemeldet")
            return
        }
        viewModelScope.launch {
            try {
                repo.addFavoriteTip(uid, tip)
            } catch (e: Exception) {
                Log.e("FavTipViewModel", "Fehler beim Hinzufügen des FavTips: ${e.toString()}")
            }
        }
    }

    fun removeFavoriteTip(tip: Tip) {
        val uid = userId
        if (uid == null) {
            Log.e("FavTipViewModel", "Kein User angemeldet. FavTip kann nicht gelöscht werden.")
            return
        }
        repo.removeFavoriteTip(uid, tip)
    }
}