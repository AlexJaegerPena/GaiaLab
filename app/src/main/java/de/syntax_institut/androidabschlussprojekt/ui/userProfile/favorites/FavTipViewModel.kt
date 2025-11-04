package de.syntax_institut.androidabschlussprojekt.ui.userProfile.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavTipRepository
import de.syntax_institut.androidabschlussprojekt.service.AuthService
import kotlinx.coroutines.launch

class FavTipViewModel(
    private val repo: FavTipRepository,
    private val authService: AuthService
): ViewModel() {

    private var userId: String? = null

    val favTips = repo.favTips

    init {
        viewModelScope.launch {
            authService.authState.collect { user ->
                userId = user?.uid
                if (userId != null) {
                    repo.listenToFavorites(userId!!)
                } else {
                    Log.e("FavTipViewModel", "Kein User angemeldet.")
                }
            }
        }
    }

    fun toggleFavorite(isFavorite: Boolean, tip: Tip) {
        if (isFavorite) {
            removeFavoriteTip(tip.id)
        } else {
            addFavoriteTip(tip.id)
        }
    }

    fun addFavoriteTip(tipId: Int) {
        val uid = userId
        if (uid == null) {
            Log.e("FavTipViewModel", "Kein User angemeldet")
            return
        }
        viewModelScope.launch {
            try {
                repo.addFavoriteTip(uid, tipId)
            } catch (e: Exception) {
                Log.e("FavTipViewModel", "Fehler beim Hinzufügen des FavTips: ${e.toString()}")
            }
        }
    }

    fun removeFavoriteTip(tipId: Int) {
        val uid = userId
        if (uid == null) {
            Log.e("FavTipViewModel", "Kein User angemeldet. FavTip kann nicht gelöscht werden.")
            return
        }
        repo.removeFavoriteTip(uid, tipId)
    }
}