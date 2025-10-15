package de.syntax_institut.androidabschlussprojekt.ui.ecoHub.ecoTips

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import de.syntax_institut.androidabschlussprojekt.data.repository.MyAPIRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EcoTipsViewModel(
    private val repo: MyAPIRepository
): ViewModel() {

    private val _tips = MutableStateFlow<List<Tip>>(emptyList())
    val tips = _tips.asStateFlow()

    private val _randomTip = MutableStateFlow<Tip?>(null)
    val randomTip = _randomTip.asStateFlow()

    init {
        getAllTips()
    }

    fun getAllTips() {
        viewModelScope.launch {
            try {
                _tips.value = repo.getEcoTips()
            } catch (e: Exception) {
                Log.e("EcoTipsViewModel", e.toString())
            }
        }
    }

    fun getRandomTip() {
        viewModelScope.launch {
            try {
                _randomTip.value = repo.getRandomEcoTip()
            } catch (e: Exception) {
                Log.e("EcoTipsViewModel", e.toString())
            }
        }
    }
}