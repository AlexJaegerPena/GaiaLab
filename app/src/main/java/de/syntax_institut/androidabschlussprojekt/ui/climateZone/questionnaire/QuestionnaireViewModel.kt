package de.syntax_institut.androidabschlussprojekt.ui.climateZone.questionnaire

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.questionnaire.Question
import de.syntax_institut.androidabschlussprojekt.data.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// application, da ich hier nicht auf context zugreifen kann aber application ist selbst ein context
class QuestionnaireViewModel(
    private val repository: QuestionnaireRepository
) : ViewModel() {

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions = _questions.asStateFlow()

    private val _actualQuestion = MutableStateFlow<Question?>(null)
    val actualQuestion = _actualQuestion.asStateFlow()

    private val _selectedAnswerId = MutableStateFlow<Int?>(null)
    val selectedAnswerId = _selectedAnswerId.asStateFlow()

    private val _userResponses = mutableMapOf<Int, Int>()
    val userResponses = _userResponses

    private val _navigateToResult = MutableStateFlow(false)
    val navigateToResult = _navigateToResult.asStateFlow()

    init {
        loadQuestions()
    }

    fun loadQuestions() {
        viewModelScope.launch {
            _questions.value = repository.loadQuestionnaire()
        }
        _actualQuestion.value = _questions.value.first()
    }

    fun nextQuestion() {
        val currentIndex = _questions.value.indexOf(_actualQuestion.value)
        if (currentIndex < _questions.value.size - 1 && _selectedAnswerId != null) {
            _actualQuestion.value = _questions.value[currentIndex + 1]
        } else {
            showResult()
        }
        _selectedAnswerId.value = null
    }

    fun previousQuestion() {
        val currentIndex = _questions.value.indexOf(_actualQuestion.value)
        if (currentIndex > 0) {
            _actualQuestion.value = _questions.value[currentIndex - 1]
        }
    }

    fun saveQAPairs(questionId: Int, answerId: Int) {
        _userResponses[questionId] = answerId
        _selectedAnswerId.value = answerId
    }

    fun showResult() {
        if (_selectedAnswerId.value != null) {
            _navigateToResult.value = true
        }
    }

    fun onNavigatedToResult() {
        _navigateToResult.value = false
    }


    fun getUserResponses() {

    }

    // so auslesen
    // val savedAnswerId = _userResponses[questionId]
}