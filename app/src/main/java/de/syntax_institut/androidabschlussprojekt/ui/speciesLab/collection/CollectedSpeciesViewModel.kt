package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CollectedSpeciesRepository
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import kotlinx.coroutines.launch

class CollectedSpeciesViewModel(
    private val repo: CollectedSpeciesRepository,
    private val authVM: AuthViewModel
) : ViewModel() {

    private var userId: String? = null

    val collectedSpecies = repo.collectedSpecies

    init {
        viewModelScope.launch {
            authVM.currentUser.collect { user ->
                userId = user?.uid
                if (userId != null) {
                    repo.listenToSpeciesCollection(userId!!)
                } else {
                    Log.e("CollectedSpeciesViewModel", "Kein User angemeldet.")
                }
            }
        }
    }

    fun addSpeciesToCollection(imageUrl: String, name: String) {
        val uid = userId
        if (uid == null) {
            Log.e("CollectedSpeciesViewModel", "Kein User angemeldet.")
            return
        } else {
            viewModelScope.launch {
                try {
                    repo.addSpeciesToCollection(uid, imageUrl, name)
                } catch (e: Exception) {
                    Log.e("CollectedSpeciesViewModel", "Fehler beim Hinzufügen der CollectedSpecies: ${e.toString()}")

                }
            }
        }
    }

    fun removeCollectedSpecies(speciesId: String) {
        val uid = userId
        if (uid == null) {
            Log.e("CollectedSpeciesViewModel", "Kein User angemeldet.")
            return
        } else {
            repo.removeCollectedSpecies(uid, speciesId)
        }
    }
}