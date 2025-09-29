package de.syntax_institut.androidabschlussprojekt.data.repository

import de.syntax_institut.androidabschlussprojekt.data.model.ApiResponse
import de.syntax_institut.androidabschlussprojekt.data.remote.ApiService
import okhttp3.MultipartBody


class SpeciesRepository(private val api: ApiService) {

        suspend fun identifySpecies(imagePart: MultipartBody.Part): ApiResponse? {
            val response = api.identifySpecies(imagePart)
            return if (response.isSuccessful) response.body() else null
        }
}