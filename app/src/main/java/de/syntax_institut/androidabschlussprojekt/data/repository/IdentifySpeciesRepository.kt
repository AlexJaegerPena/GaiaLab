package de.syntax_institut.androidabschlussprojekt.data.repository

import de.syntax_institut.androidabschlussprojekt.data.model.SpeciesApiResponse
import de.syntax_institut.androidabschlussprojekt.data.remote.SPECIESAPI
import de.syntax_institut.androidabschlussprojekt.data.remote.SpeciesAPIService
import okhttp3.MultipartBody


class IdentifySpeciesRepository(private val api: SpeciesAPIService) {


        suspend fun identifySpecies(imagePart: MultipartBody.Part): SpeciesApiResponse? {
            val response = api.identifySpecies(imagePart)
            return if (response.isSuccessful) response.body() else null
        }
}