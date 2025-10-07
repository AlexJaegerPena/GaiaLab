package de.syntax_institut.androidabschlussprojekt.ui.species

import android.R.attr.name
import android.graphics.Bitmap
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.SpeciesApiResponse
import de.syntax_institut.androidabschlussprojekt.data.model.SpeciesCollectionItem
import de.syntax_institut.androidabschlussprojekt.data.repository.SpeciesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import kotlin.math.ceil

class SpeciesViewModel(
    private val repository: SpeciesRepository
) : ViewModel() {


    private val _result = MutableStateFlow<SpeciesApiResponse?>(null)
    val result = _result.asStateFlow()

    private val _uploadedBitmap = MutableStateFlow<Bitmap?>(null)
    var uploadedBitmap = _uploadedBitmap.asStateFlow()

    private val _newSpeciesItem = MutableStateFlow<SpeciesCollectionItem?>(null)
    val newSpeciesItem = _newSpeciesItem.asStateFlow()

    private val _speciesCollection = MutableStateFlow<List<SpeciesCollectionItem>>(emptyList())
    val speciesCollection = _speciesCollection.asStateFlow()


    fun identify(image: MultipartBody.Part) {
        viewModelScope.launch {
            val response = repository.identifySpecies(image)
            _result.value = response
        }
    }

    fun saveSpecies(result: SpeciesApiResponse, uploadedBitmap: Bitmap) {
        val list = result.predictions.firstOrNull()?.taxa?.items
        val firstTwo = list?.take(2)

        val newSpeciesName = firstTwo?.joinToString("\n") {
            "${it.scientific_name}: ${ceil(it.probability * 100).toInt()} %"
        } ?: ""

        _speciesCollection.value = _speciesCollection.value + SpeciesCollectionItem(
            name = newSpeciesName,
            image = uploadedBitmap
        )
    }
}