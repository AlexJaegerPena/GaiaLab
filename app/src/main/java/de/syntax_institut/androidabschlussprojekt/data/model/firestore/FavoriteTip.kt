package de.syntax_institut.androidabschlussprojekt.data.model.firestore

data class FavoriteTip(
    val factId: Int,
    val savedAt: Long = System.currentTimeMillis()
)