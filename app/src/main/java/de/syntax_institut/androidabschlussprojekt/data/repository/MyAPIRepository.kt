package de.syntax_institut.androidabschlussprojekt.data.repository

import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import de.syntax_institut.androidabschlussprojekt.data.remote.MYAPI
import de.syntax_institut.androidabschlussprojekt.data.remote.MyAPIService

class MyAPIRepository(private val api: MyAPIService) {



    // ----- Species -----
    suspend fun getSpeciesFacts(): List<Fact> {
        return api.getSpeciesFacts().data
    }

    suspend fun getRandomSpeciesFact(): Fact {
        return api.getRandomSpeciesFact().data
    }

    // ----- Climate -----
    suspend fun getClimateFacts(): List<Fact> {
        return api.getClimateFacts().data
    }

    suspend fun getRandomClimateFact(): Fact {
        return api.getRandomClimateFact().data
    }

    suspend fun getClimateTips(): List<Tip> {
        return api.getEcoTips().data
    }

    suspend fun getRandomClimateTip(): Tip {
        return api.getRandomEcoTip().data
    }


    // ----- Eco -----
    suspend fun getEcoFacts(): List<Fact> {
        return api.getEcoFacts().data
    }

    suspend fun getRandomEcoFact(): Fact {
        return api.getRandomEcoFact().data
    }

    suspend fun getEcoTips(): List<Tip> {
        return api.getEcoTips().data
    }

    suspend fun getRandomEcoTip(): Tip {
        return api.getRandomEcoTip().data
    }
}