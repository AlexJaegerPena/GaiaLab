package de.syntax_institut.androidabschlussprojekt.data.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

data class SpeciesCollectionItem (
    val id: UUID = UUID.randomUUID(),
    val image: Bitmap?,
    val name: String
)
