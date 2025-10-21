package de.syntax_institut.androidabschlussprojekt.data.repository.firestore

import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val db: FirebaseFirestore
) {

    private val userRef = db.collection("users")

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    suspend fun createUser(user: User) {
        userRef
            .document(user.userId)
            .set(user)
            .await()
    }

    suspend fun deleteUser(user: User) {
        userRef
            .document(user.userId)
            .delete()
            .await()
    }

    fun listenToCurrentUser(userId: String) {
        val docRef = userRef.document(userId)
        docRef.addSnapshotListener { data, error ->
            val user = data?.toObject(User::class.java)
            user?.let {
                _currentUser.value = it
            }
        }
    }
}