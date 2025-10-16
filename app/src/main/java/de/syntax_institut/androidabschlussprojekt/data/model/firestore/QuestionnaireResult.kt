package de.syntax_institut.androidabschlussprojekt.data.model.firestore

import java.util.UUID

data class QuestionnaireResult(
    val id: UUID = UUID.randomUUID(),
    val qaPair: Map<String, String> = emptyMap(),
    val co2Score: Double = 0.0
)