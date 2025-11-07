package de.syntax_institut.androidabschlussprojekt.data.model.firestore

import java.util.UUID

data class CO2Result(
    val quizId: String = UUID.randomUUID().toString(),
    val qaPair: Map<String, Int> = emptyMap(),
    val co2Score: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis()
)





