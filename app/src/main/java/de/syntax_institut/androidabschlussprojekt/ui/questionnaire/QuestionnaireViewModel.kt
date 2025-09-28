package de.syntax_institut.androidabschlussprojekt.ui.questionnaire

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.Question
import de.syntax_institut.androidabschlussprojekt.data.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// application, da ich hier nicht auf context zugreifen kann aber application ist selbst ein context
class QuestionnaireViewModel(application: Application) : AndroidViewModel(application) {

    val repository = QuestionnaireRepository(context = application)

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions = _questions.asStateFlow()

    init {
        loadQuestions()
    }

    fun loadQuestions() {
        viewModelScope.launch {
            _questions.value = repository.loadQuestionnaire()
        }
    }
}