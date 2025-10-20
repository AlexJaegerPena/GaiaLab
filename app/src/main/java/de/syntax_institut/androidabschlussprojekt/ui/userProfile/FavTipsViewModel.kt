package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavTipsViewModel(
    private val db: FirebaseFirestore
): ViewModel() {


    private val _favTips = MutableStateFlow<List<Fact>>(emptyList())
    val favTips = _favTips.asStateFlow()

    init {
    }

    fun addFavoriteTip(tip: Tip) {

    }

    fun removeFavoriteTip(tip: Tip) {
    }
}