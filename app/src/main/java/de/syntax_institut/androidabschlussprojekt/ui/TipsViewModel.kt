package de.syntax_institut.androidabschlussprojekt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.FavoriteFact
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.FavoriteTip
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import de.syntax_institut.androidabschlussprojekt.data.repository.api.MyAPIRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.map

class TipsViewModel(
    private val repo: MyAPIRepository,
): ViewModel() {

    private val _tips = MutableStateFlow<List<Tip>>(emptyList())
    val tips = _tips.asStateFlow()

    private val _randomTip = MutableStateFlow<Tip?>(null)
    val randomTip = _randomTip.asStateFlow()

    private val _category = MutableStateFlow<String>("")
    val category = _category.asStateFlow()


    fun setCategory(newCategory: String) {
        _category.value = newCategory
        getTips(newCategory)
    }

    fun getTips(category: String) {
        viewModelScope.launch {
            try {
                _tips.value = repo.getTips(category)
            } catch (e: Exception) {
                Log.e("EcoTipsViewModel", e.toString())
            }
        }
    }

    fun getTipsById(favoriteTips: List<FavoriteTip>): List<Tip> {
        val ids = favoriteTips.map { it.id }
        val filteredTips = _tips.value.filter { it.id in ids }
        return filteredTips
    }

    fun getRandomTip(category: String) {
        viewModelScope.launch {
            try {
                _randomTip.value = repo.getRandomTip(category)
            } catch (e: Exception) {
                Log.e("EcoTipsViewModel", e.toString())
            }
        }
    }
}