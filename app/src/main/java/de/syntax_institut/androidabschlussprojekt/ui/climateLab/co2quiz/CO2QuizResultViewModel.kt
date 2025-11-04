package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.CO2Result
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CO2QuizResultRepository
import de.syntax_institut.androidabschlussprojekt.service.AuthService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CO2QuizResultViewModel(
    private val repo: CO2QuizResultRepository,
    private val authService: AuthService
): ViewModel() {

    private var userId: String? = null

    private val _lastResult = MutableStateFlow<CO2Result?>(null)
    val lastResult = _lastResult.asStateFlow()

    private val _resultSaved = MutableStateFlow(false)
    val resultSaved = _resultSaved.asStateFlow()

    init {
        viewModelScope.launch {
            authService.authState.collect { user ->
                userId = user?.uid
                if (userId != null) {
                    listenToResults(userId!!)
                } else {
                    Log.e("CO2QuizResultViewModel", "Kein User angemeldet.")
                }
            }
        }
    }


    private fun listenToResults(uid: String) {
        viewModelScope.launch {
            repo.listenToCO2Results(uid)
            repo.co2Results.collect { results -> // abfangen der ergebnisse sobald änderung
                _lastResult.value = results.lastOrNull() // und speichern des letzten
            }
        }
    }

    fun addCO2QuizResult(qaPair: Map<Int, Int>, co2Score: Double) {
        val uid = userId
        if (uid == null) {
            Log.e("CO2QuizResultViewModel", "Kein User angemeldet.")
            return
        }
        viewModelScope.launch {
            try {
                repo.addCO2Result(uid, qaPair, co2Score)
                delay(300)
                _resultSaved.value = true
            } catch (e: Exception) {
                Log.e("CO2QuizResultViewModel", "Fehler beim Hinzufügen des Results: ${e.toString()}")
            }
        }
    }

    fun resetResultSaved() {
        _resultSaved.value = false
    }

    fun removeQuizResult(resultId: String) {
        val uid = userId
        if (uid == null) {
            Log.e("CO2QuizResultViewModel", "Kein User angemeldet.")
            return
        }
        repo.removeCO2Result(uid, resultId)
    }
}