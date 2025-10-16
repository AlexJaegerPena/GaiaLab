package de.syntax_institut.androidabschlussprojekt.data.model.firestore

import android.graphics.Bitmap
import java.util.UUID

data class CollectedSpecies (
    val speciesId: UUID = UUID.randomUUID(),
    val image: Bitmap?,
    val name: String,
    val savedAt: Long = System.currentTimeMillis()
)