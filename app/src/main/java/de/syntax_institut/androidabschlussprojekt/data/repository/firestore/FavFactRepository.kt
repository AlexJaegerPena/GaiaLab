package de.syntax_institut.androidabschlussprojekt.data.repository.firestore

import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.FavoriteFact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import kotlin.jvm.java

class FavFactRepository(
    private val db: FirebaseFirestore
) {

    private val collectionPath = "favoriteFacts"

    private val _favFacts = MutableStateFlow(listOf<FavoriteFact>())
    val favFacts = _favFacts.asStateFlow()

    suspend fun addFavoriteFact(userId: String, fact: Fact) {
        val userRef = db
            .collection("users")
            .document(userId)

        val newFav = FavoriteFact(fact.id)
        userRef
            .collection(collectionPath)
            .document(fact.id.toString())
            .set(newFav)
            .await()
    }


    fun removeFavoriteFact(userId: String, fact: Fact) {
        val userRef = db
            .collection("users")
            .document(userId)

        userRef
            .collection(collectionPath)
            .document(fact.id.toString())
            .delete()
    }

    fun listenToFavorites(userId: String) {
        val userRef = db
            .collection("users")
            .document(userId)

        userRef
            .collection(collectionPath)
            .addSnapshotListener { data, error ->
                val list = data?.toObjects(FavoriteFact::class.java)
                list?.let {
                    _favFacts.value = it
                }
            }
    }
}