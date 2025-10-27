package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.facts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.repository.api.MyAPIRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpeciesFactsViewModel(
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
                _facts.value = repo.getSpeciesFacts()
                Log.d("SpeciesFactsViewModel", _facts.value.toString())
            } catch (e: Exception) {
                Log.e("SpeciesFactsViewModel", e.toString())
            }
        }
    }

    fun getRandomFact() {
        viewModelScope.launch {
            try {
                _randomFact.value = repo.getRandomSpeciesFact()
            } catch (e: Exception) {
                Log.e("SpeciesFactsViewModel", e.toString())
            }
        }
    }
}