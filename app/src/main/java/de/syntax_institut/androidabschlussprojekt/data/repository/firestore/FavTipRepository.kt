package de.syntax_institut.androidabschlussprojekt.data.repository.firestore

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.FavoriteTip
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavTipRepository(
    private val db: FirebaseFirestore,
) {

    private val collectionPath = "favoriteTipIds"

    private val _favTips = MutableStateFlow(listOf<FavoriteTip>())
    val favTips = _favTips.asStateFlow()


    fun addFavoriteTip(userId: String, tip: Tip) {
        val userRef = db
            .collection("users")
            .document(userId)

        val newTip = FavoriteTip(tip.id)
        userRef
            .collection(collectionPath)
            .document(tip.id.toString())
            .set(newTip)
            .addOnFailureListener { e -> Log.e("FavTipRepo", "Fehler beim Hinzufügen", e) }
    }

    fun removeFavoriteTip(userId: String, tip: Tip) {
        val userRef = db
            .collection("users")
            .document(userId)

        userRef
            .collection(collectionPath)
            .document(tip.id.toString())
            .delete()
    }

    fun listenToFavorites(userId: String) {
        val userRef = db
            .collection("users")
            .document(userId)

        userRef
            .collection(collectionPath)
            .addSnapshotListener { data, error ->
                val list = data?.toObjects(FavoriteTip::class.java)
                list?.let {
                    _favTips.value = it
                }
            }
    }

}