package de.syntax_institut.androidabschlussprojekt.data.repository.firestore

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val db = FirebaseFirestore.getInstance()
    private val userRef = db.collection("users")

    val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
    val currentUserRef = userRef.document(currentUserUid!!)

    

}