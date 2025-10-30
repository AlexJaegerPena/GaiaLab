package de.syntax_institut.androidabschlussprojekt.data.model.co2quiz

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    var id: Int,
    val text: String,
    val answers: List<Answer>,
    val category: String
)

