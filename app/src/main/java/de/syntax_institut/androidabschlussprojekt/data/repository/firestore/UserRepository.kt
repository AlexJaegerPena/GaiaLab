package de.syntax_institut.androidabschlussprojekt.data.repository.firestore

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserRepository(
    private val db: FirebaseFirestore
) {

    private val userRef = db.collection("users")

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    fun createUser(userId: String, email: String, username: String) {
        val newUser = User(
            userId = userId,
            email = email,
            username = username
        )
        userRef
            .document(userId).set(newUser)
            .addOnSuccessListener {
                Log.d("UserRepository", "User created successfully")
            }
            .addOnFailureListener { e ->
                Log.e("UserRepository", "Error creating user", e)
            }
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