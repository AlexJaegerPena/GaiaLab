package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavFactRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavTipRepository

class FavFactsViewModel(
    private val userId: String
): ViewModel() {



    init {

    }

    fun addFavoriteFact(fact: Fact) {

    }

    fun removeFavoriteFact(fact: Fact) {

    }
}