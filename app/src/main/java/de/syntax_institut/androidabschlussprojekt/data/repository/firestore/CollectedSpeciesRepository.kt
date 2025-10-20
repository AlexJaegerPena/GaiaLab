package de.syntax_institut.androidabschlussprojekt.data.repository.firestore

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.CollectedSpecies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CollectedSpeciesRepository(
    private val db: FirebaseFirestore,
) {

    private val collectionPath = "collectedSpecies"

    private val _collectedSpecies = MutableStateFlow(listOf<CollectedSpecies>())
    val collectedSpecies = _collectedSpecies.asStateFlow()

    fun addSpeciesToCollection(userId: String, image: Bitmap, name: String) {
        val userRef = db
            .collection("users")
            .document(userId)

        val newSpecies = CollectedSpecies(image = image, name = name)
        userRef
            .collection(collectionPath)
            .document(newSpecies.speciesId)
            .set(newSpecies)
            .addOnFailureListener { e -> Log.e("CollectedSpeciesCollection", "Fehler beim Hinzufügen", e)}
    }

    fun removeCollectedSpecies(userId: String, id: String) {
        val userRef = db
            .collection("users")
            .document(userId)

        userRef
            .collection(collectionPath)
            .document(id)
            .delete()
    }

    fun listenToSpeciesCollection(userId: String) {
        val userRef = db
            .collection("users")
            .document(userId)

        userRef
            .collection(collectionPath)
            .addSnapshotListener { data, error ->
                val list = data?.toObjects(CollectedSpecies::class.java)
                list?.let {
                    _collectedSpecies.value = it
                }
            }
    }
}