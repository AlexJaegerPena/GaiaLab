package de.syntax_institut.androidabschlussprojekt.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithAnswers(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val answers: List<UserAnswer>
)