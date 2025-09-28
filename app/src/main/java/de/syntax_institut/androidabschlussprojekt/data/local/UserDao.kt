package de.syntax_institut.androidabschlussprojekt.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import de.syntax_institut.androidabschlussprojekt.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(user: User)

   @Query("SELECT * FROM user WHERE id = :userId")
   fun getUser(userId: Int): Flow<User>

   @Delete
   suspend fun delete(user: User)

   @Update
   suspend fun update(user: User)

}