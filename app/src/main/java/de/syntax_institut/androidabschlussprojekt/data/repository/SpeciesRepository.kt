package de.syntax_institut.androidabschlussprojekt.data.repository

import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.remote.MYAPI

class SpeciesRepository {
    private val api: MYAPI = MYAPI

    suspend fun getSpeciesFacts(): List<Fact> {
        return api.service.getSpeciesFacts().data
    }
}