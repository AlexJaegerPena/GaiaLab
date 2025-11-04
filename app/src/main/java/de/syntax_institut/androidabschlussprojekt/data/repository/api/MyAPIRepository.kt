package de.syntax_institut.androidabschlussprojekt.data.repository.api

import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import de.syntax_institut.androidabschlussprojekt.data.remote.MyAPIService
import retrofit2.http.Path

class MyAPIRepository(private val api: MyAPIService) {


    suspend fun getFacts(@Path("category") category: String): List<Fact> {
        return api.getFacts(category).data
    }

    suspend fun getRandomFact(@Path("category") category: String): Fact {
        return api.getRandomFact(category).data
    }

    suspend fun getTips(@Path("category") category: String): List<Tip> {
        return api.getTips(category).data
    }

    suspend fun getRandomTip(@Path("category") category: String): Tip {
        return api.getRandomTip(category).data
    }
}