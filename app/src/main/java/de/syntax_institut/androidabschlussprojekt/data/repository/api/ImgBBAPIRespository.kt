package de.syntax_institut.androidabschlussprojekt.data.repository.api

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import de.syntax_institut.androidabschlussprojekt.BuildConfig
import de.syntax_institut.androidabschlussprojekt.data.remote.ImgBBApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class ImgBBAPIRespository(
    private val api: ImgBBApiService,
    private val context: Context
) {

    suspend fun uploadToImgBB(uri: Uri): String? {

        val apiKey = BuildConfig.IMGBB_API_KEY
        val file = uriToFile(uri) // Uri zu temp File
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        val response = api.uploadImage(apiKey, body)

        file.delete() // Temp file löschen

        return if (response.isSuccessful) {
            response.body()?.data?.url
        } else {
            null
        }
    }

    private fun uriToFile(uri: Uri): File {
        val contentResolver = context.contentResolver

        // Dateinamen aus Uri extrahieren oder generieren
        val fileName = getFileName(uri) ?: "upload_${System.currentTimeMillis()}.jpg"
        val tempFile = File(context.cacheDir, fileName)

        contentResolver.openInputStream(uri)?.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return tempFile
    }

    private fun getFileName(uri: Uri): String? {
        var fileName: String? = null
        val contentResolver = context.contentResolver

        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst() && nameIndex != -1) {
                fileName = cursor.getString(nameIndex)
            }
        }

        return fileName
    }
}

