package de.syntax_institut.androidabschlussprojekt.data.repository

import de.syntax_institut.androidabschlussprojekt.data.model.SpeciesApiResponse
import de.syntax_institut.androidabschlussprojekt.data.remote.ApiService
import okhttp3.MultipartBody


class SpeciesRepository(private val api: ApiService) {

        suspend fun identifySpecies(imagePart: MultipartBody.Part): SpeciesApiResponse? {
            val response = api.identifySpecies(imagePart)
            return if (response.isSuccessful) response.body() else null
        }
}