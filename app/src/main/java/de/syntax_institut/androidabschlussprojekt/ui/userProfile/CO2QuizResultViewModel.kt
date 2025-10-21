package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CO2QuizResultRepository
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import kotlinx.coroutines.launch

class CO2QuizResultViewModel(
    private val repo: CO2QuizResultRepository,
    private val authVM: AuthViewModel
): ViewModel() {

    private var userId: String? = null

    val co2Results = repo.co2Results

    init {
        viewModelScope.launch {
            authVM.currentUser.collect { user ->
                userId = user?.uid
                if (userId != null) {
                    repo.listenToCO2Results(userId!!)
                } else {
                    Log.e("CO2QuizResultViewModel", "Kein User angemeldet.")
                }
            }
        }
    }

    fun addCO2QuizResult(qaPair: Map<String, String>, co2Score: Double) {
        val uid = userId
        if (uid == null) {
            Log.e("CO2QuizResultViewModel", "Kein User angemeldet.")
            return
        }
        viewModelScope.launch {
            try {
                repo.addCO2Result(uid, qaPair, co2Score)
            } catch (e: Exception) {
                Log.e("CO2QuizResultViewModel", "Fehler beim Hinzufügen des Results: ${e.toString()}")
            }
        }
    }

    fun removeFavoriteTip(resultId: String) {
        val uid = userId
        if (uid == null) {
            Log.e("CO2QuizResultViewModel", "Kein User angemeldet.")
            return
        }
        repo.removeCO2Result(uid, resultId)
    }
}