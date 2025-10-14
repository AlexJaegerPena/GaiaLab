package de.syntax_institut.androidabschlussprojekt.data.model.questionnaire

import kotlinx.serialization.Serializable

@Serializable
data class Answer(
    val id: Int,
    val text: String,
    val factor: Double,
    val type: FactorType
)

enum class FactorType {
    ABSOLUTE, MULTIPLIER
}

