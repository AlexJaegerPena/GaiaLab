package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.identification

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.SpeciesApiResponse
import de.syntax_institut.androidabschlussprojekt.data.repository.api.IdentifySpeciesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class IdentifySpeciesViewModel(
    private val repo: IdentifySpeciesRepository
) : ViewModel() {


    private val _result = MutableStateFlow<SpeciesApiResponse?>(null)
    val result = _result.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // private val _uploadedBitmap = MutableStateFlow<Bitmap?>(null)
    // var uploadedBitmap = _uploadedBitmap.asStateFlow()


    fun identifyImage(uri: Uri?, context: Context) {
        if (uri == null) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                // 1. Bild aus der URI lesen
                val inputStream = context.contentResolver.openInputStream(uri)
                val bytes = inputStream?.readBytes()
                inputStream?.close()

                if (bytes != null) {
                    // 2. RequestBody und MultipartBody erstellen
                    val requestBody = bytes.toRequestBody("image/jpeg".toMediaTypeOrNull())
                    val multipartBody = MultipartBody.Part.createFormData(
                        name = "image",
                        filename = "photo.jpg",
                        body = requestBody
                    )

                    // API call
                    val response = repo.identifySpecies(multipartBody)
                    _result.value = response
                }
            } catch (e: Exception) {
                Log.e("IdentifySpeciesVM", "Error identifying species", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}