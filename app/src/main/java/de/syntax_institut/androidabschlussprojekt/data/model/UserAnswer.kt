package de.syntax_institut.androidabschlussprojekt.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userAnswer")
data class UserAnswer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val questionId: Int,
    val answerId: Int
)