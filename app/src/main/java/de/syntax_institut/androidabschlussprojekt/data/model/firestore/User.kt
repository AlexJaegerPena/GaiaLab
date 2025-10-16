package de.syntax_institut.androidabschlussprojekt.data.model.firestore

import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val profilePic: Int,
)