package de.syntax_institut.androidabschlussprojekt.ui.species

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.SpeciesApiResponse
import de.syntax_institut.androidabschlussprojekt.data.repository.SpeciesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class SpeciesViewModel(
    private val repository: SpeciesRepository
) : ViewModel() {


    private val _result = MutableStateFlow<SpeciesApiResponse?>(null)
    val result = _result.asStateFlow()




    fun identify(image: MultipartBody.Part) {
        viewModelScope.launch {
            val response = repository.identifySpecies(image)
            _result.value = response
        }
    }
}