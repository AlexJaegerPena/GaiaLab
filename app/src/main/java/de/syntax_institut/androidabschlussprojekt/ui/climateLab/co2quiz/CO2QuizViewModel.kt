package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.Answer
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.FactorType
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.Question
import de.syntax_institut.androidabschlussprojekt.data.repository.local.CO2QuizRepository
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.CO2QuizResultViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init

// application, da ich hier nicht auf context zugreifen kann aber application ist selbst ein context
class CO2QuizViewModel(
    private val repository: CO2QuizRepository,
) : ViewModel() {

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions = _questions.asStateFlow()

    private val _actualQuestion = MutableStateFlow<Question?>(null)
    val actualQuestion = _actualQuestion.asStateFlow()

    private val _userResponses = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val userResponses = _userResponses.asStateFlow()

    private val _score = MutableStateFlow(0.0)
    val score = _score.asStateFlow()


    init {
        loadQuestions()
    }

    fun loadQuestions() {
        viewModelScope.launch {
            _questions.value = repository.loadCO2Quiz()
        }
        _actualQuestion.value = _questions.value.first()
    }

    fun nextQuestion() {
        val currentIndex = _questions.value.indexOf(_actualQuestion.value)
        if (currentIndex < _questions.value.size - 1) {
            _actualQuestion.value = _questions.value[currentIndex + 1]
        }
    }

    fun previousQuestion() {
        val currentIndex = _questions.value.indexOf(_actualQuestion.value)
        if (currentIndex > 0) {
            _actualQuestion.value = _questions.value[currentIndex - 1]
        }
    }

    fun saveQAPairs(questionId: Int, answerId: Int) {
        _userResponses.value = _userResponses.value.toMutableMap().apply {
            this[questionId] = answerId
        }
    }

    fun updateScore() {
        _score.value = calculateScore()
    }

    fun calculateScore(): Double {
        var totalScore: Double = 0.0

        _userResponses.value.forEach { (questionId, answerId) ->

            val question = _questions.value.find { it.id == questionId } ?: return@forEach //wenn null dann über nächstes iterieren
            val answer = question?.answers?.find { it.id == answerId } ?: return@forEach

            if (answer.type == FactorType.ABSOLUTE) {
                totalScore += answer.factor
            } else if (answer.type == FactorType.MULTIPLIER) {
                totalScore *= answer.factor
            }
        }
        return totalScore
    }
}