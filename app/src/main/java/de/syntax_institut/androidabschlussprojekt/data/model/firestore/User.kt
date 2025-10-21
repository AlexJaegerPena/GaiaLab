package de.syntax_institut.androidabschlussprojekt.data.model.firestore

data class User(
    val userId: String = "",
    val username: String? = null,
    val email: String? = null,
)
