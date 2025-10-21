package de.syntax_institut.androidabschlussprojekt.data.model.firestore

import java.util.UUID

data class CollectedSpecies (
    val speciesId: String = UUID.randomUUID().toString(),
    val image: Int? = null,
    val name: String = "",
    val savedAt: Long = System.currentTimeMillis()
)