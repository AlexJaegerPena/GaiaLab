package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CollectedSpeciesRepository
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.UserViewModel
import kotlinx.coroutines.launch

/*
class SpeciesCollectionViewModel(
    private val repo: CollectedSpeciesRepository,
    private val userVM: UserViewModel
): ViewModel() {

    private var userId: String? = null

    val collectedSpecies = repo.collectedSpecies

    init {
        viewModelScope.launch {
            userVM.currentUser.collect { user ->
                userId = user?.userId
                if (userId != null) {
                    repo.listenToSpeciesCollection(userId!!)
                } else {
                    Log.e("SpeciesCollectionViewModel", "Kein User angemeldet.")
                }
            }
        }
    }

    fun addSpeciesToCollection(image: String, name: String) {
        val uid = userId
        if (uid == null) {
            Log.e("FavFactViewModel", "Kein User angemeldet. Species kann nicht gespeichert werden.")
            return
        }
        viewModelScope.launch {
            try {
                repo.addSpeciesToCollection(uid, image, name)
            } catch (e: Exception){
                Log.e("SpeciesCollectionViewModel", e.toString())
            }
        }
    }

    fun deleteSpeciesFromCollection(speciesId: String) {
        val uid = userId
        if (uid == null) {
            Log.e("FavFactViewModel", "Kein User angemeldet. Species kann nicht gelöscht werden.")
            return
        }
        viewModelScope.launch {
            try {
                repo.removeCollectedSpecies(uid, speciesId)
            } catch (e: Exception){
                Log.e("SpeciesCollectionViewModel", e.toString())
            }
        }
    }
}

 */