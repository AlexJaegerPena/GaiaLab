package de.syntax_institut.androidabschlussprojekt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import de.syntax_institut.androidabschlussprojekt.data.model.UserAnswer
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAnswerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userAnswer: UserAnswer)

    @Transaction
    @Query("SELECT * FROM UserAnswer WHERE userId = userId")
    fun getAnswersForUser(UserId: Int): Flow<List<UserAnswer>>
}