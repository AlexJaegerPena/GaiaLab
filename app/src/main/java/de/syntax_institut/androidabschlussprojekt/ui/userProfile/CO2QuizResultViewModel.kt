package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.CO2Result
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CO2QuizResultRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavFactRepository

class CO2QuizResultViewModel(
    private val userId: String
) {


    init {
    }

    fun addCO2QuizResult(qaPair: Map<String, String>, co2Score: Double) {
    }

    fun removeFavoriteTip(id: String) {
    }
}