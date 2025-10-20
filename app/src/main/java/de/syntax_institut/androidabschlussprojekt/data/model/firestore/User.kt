package de.syntax_institut.androidabschlussprojekt.data.model.firestore

import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import java.util.UUID

data class User(
    val userId: String = "",
    val username: String? = null,
    val email: String? = null,
    val favoriteFacts: List<String> = emptyList(),
    val favoriteTips: List<String> = emptyList()
)
