package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.repository.api.ImgBBAPIRespository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ImgBBViewModel(
    private val repo: ImgBBAPIRespository
) : ViewModel() {

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl = _imageUrl.asStateFlow()

    private val _isUploading = MutableStateFlow(false)
    val isUploading = _isUploading.asStateFlow()


    fun uploadImage(uri: Uri) {
        if (uri == null) return

        viewModelScope.launch {
            _isUploading.value = true
            try {
                val imgUrl = repo.uploadToImgBB(uri)
                _imageUrl.value = imgUrl
            } catch (e: Exception) {
                Log.e("ImgurViewModel", "Fehler beim Upload des Bildes: ${e.toString()}")
            } finally {
                _isUploading.value = false
            }
        }
    }
}