package de.syntax_institut.androidabschlussprojekt.data.model

import de.syntax_institut.androidabschlussprojekt.R

data class Question(
    var id: Int,
    val text: String,
    val answers: List<Answer>,
    val category: String
)


enum class QuestionCategory(val bgImg: Int) {
    MOBILITY(R.drawable.q_consumption_bg), //TODO: Images einfügen
    HOUSING(R.drawable.q_consumption_bg),
    NUTRITION(R.drawable.q_consumption_bg),
    CONSUMPTION(R.drawable.q_consumption_bg),
}

