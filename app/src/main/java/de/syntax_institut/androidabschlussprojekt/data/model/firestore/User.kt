package de.syntax_institut.androidabschlussprojekt.data.model.firestore

data class User(
    val userId: String = "",
    val username: String? = null,
    val email: String? = null,
    val favoriteFactIds: List<String> = emptyList(),
    val favoriteTipIds: List<String> = emptyList(),
    val co2QuizResult: List<CO2Result> = emptyList(),
    val collectedSpecies: List<CollectedSpecies> = emptyList()
)
