package de.syntax_institut.androidabschlussprojekt.data.local

import android.R.attr.version
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntax_institut.androidabschlussprojekt.data.model.User
import kotlin.jvm.java

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "user_database"
                ).build().also { Instance = it }
            }
        }
    }
}