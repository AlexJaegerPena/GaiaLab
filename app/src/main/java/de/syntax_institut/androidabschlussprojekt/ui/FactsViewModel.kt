package de.syntax_institut.androidabschlussprojekt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.FavoriteFact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.repository.api.MyAPIRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FactsViewModel(
    private val repo: MyAPIRepository,
): ViewModel() {

    private val _facts = MutableStateFlow<List<Fact>>(emptyList())
    val facts = _facts.asStateFlow()

    private val _randomFact = MutableStateFlow<Fact?>(null)
    val randomFact = _randomFact.asStateFlow()

    private val _category = MutableStateFlow<String>("")
    val category = _category.asStateFlow()


    fun setCategory(newCategory: String) {
        _category.value = newCategory
        getFacts(newCategory)
    }

    fun getFacts(category: String) {
        viewModelScope.launch {
            try {
                _facts.value = repo.getFacts(category)
                Log.d("FactsViewModel", _facts.value.toString())
            } catch (e: Exception) {
                Log.e("FactsViewModel", e.toString())
            }
        }
    }

    fun getFactsById(favoriteFacts: List<FavoriteFact>): List<Fact> {
        val ids = favoriteFacts.map { it.id }
        val filteredFacts = _facts.value.filter { it.id in ids }
        return filteredFacts
    }

    fun getRandomFact(category: String) {
        viewModelScope.launch {
            try {
              _randomFact.value = repo.getRandomFact(category)
            } catch (e: Exception) {
                Log.e("FactsViewModel", e.toString())
            }
        }
    }
}