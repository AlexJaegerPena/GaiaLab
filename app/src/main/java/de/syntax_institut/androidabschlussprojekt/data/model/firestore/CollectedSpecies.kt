package de.syntax_institut.androidabschlussprojekt.data.model.firestore

import android.graphics.Bitmap
import java.util.UUID

data class CollectedSpecies (
    val speciesId: String = UUID.randomUUID().toString(),
    val image: Bitmap? = null,
    val name: String = "",
    val savedAt: Long = System.currentTimeMillis()
)