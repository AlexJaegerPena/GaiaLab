package de.syntax_institut.androidabschlussprojekt.data.repository.firestore

import android.util.Log
import androidx.compose.runtime.State
import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.CO2Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CO2QuizResultRepository(
    private val db: FirebaseFirestore,
) {

    private val collectionPath = "co2QuizResult"

    private val _co2Results = MutableStateFlow(listOf<CO2Result>())
    val co2Results = _co2Results.asStateFlow()

    val lastResult: CO2Result?
        get() = co2Results.value.lastOrNull()


    fun addCO2Result(userId: String, qaPair: Map<Int, Int>, co2Score: Double) {
        val userRef = db
            .collection("users")
            .document(userId)

        val newResult = CO2Result(
            qaPair = qaPair.mapKeys { it.key.toString() },  // wegen Firebase - String statt Int als Key benötigt
            co2Score = co2Score
        )
        userRef
            .collection(collectionPath)
            .document(newResult.quizId)
            .set(newResult)
            .addOnFailureListener { e -> Log.e("CO2QuizResultRepository", "Fehler beim Hinzufügen", e)}
    }

    fun removeCO2Result(userId: String, resultId: String) {
        val userRef = db
            .collection("users")
            .document(userId)

        userRef
            .collection(collectionPath)
            .document(resultId)
            .delete()
    }

    fun listenToCO2Results(userId: String) {
        val userRef = db
            .collection("users")
            .document(userId)

        userRef
            .collection(collectionPath)
            .addSnapshotListener { data, error ->
                val list = data?.toObjects(CO2Result::class.java)
                list?.let {
                    _co2Results.value = it
                }
            }
    }
}