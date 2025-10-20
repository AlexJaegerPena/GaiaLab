package de.syntax_institut.androidabschlussprojekt.data.model.co2quiz

import de.syntax_institut.androidabschlussprojekt.R
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    var id: Int,
    val text: String,
    val answers: List<Answer>,
    val category: String
)


enum class QuestionCategory(val bgImg: Int) {
    MOBILITY(R.drawable.bg_home), //TODO: Images einfügen
    HOUSING(R.drawable.bg_home),
    NUTRITION(R.drawable.bg_home),
    CONSUMPTION(R.drawable.bg_home),
}

