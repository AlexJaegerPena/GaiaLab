package de.syntax_institut.androidabschlussprojekt.ui.climateZone.climateFacts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.repository.api.MyAPIRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClimateFactsViewModel(
    private val repo: MyAPIRepository
): ViewModel() {

    private val _facts = MutableStateFlow<List<Fact>>(emptyList())
    val facts = _facts.asStateFlow()

    private val _randomFact = MutableStateFlow<Fact?>(null)
    val randomFact = _randomFact.asStateFlow()

    init {
        getAllFacts()
    }

    fun getAllFacts() {
        viewModelScope.launch {
            try {
                _facts.value = repo.getClimateFacts()
            } catch (e: Exception) {
                Log.e("ClimateFactsViewModel", e.toString())
            }
        }
    }

    fun getRandomFact() {
        viewModelScope.launch {
            try {
                _randomFact.value = repo.getRandomClimateFact()
            } catch (e: Exception) {
                Log.e("ClimateFactsViewModel", e.toString())
            }
        }
    }
}