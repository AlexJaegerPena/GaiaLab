package de.syntax_institut.androidabschlussprojekt.data.repository.local

import android.content.Context
import de.syntax_institut.androidabschlussprojekt.data.model.questionnaire.Question
import kotlinx.serialization.json.Json

class QuestionnaireRepository(private val context: Context) { // context um auf assets zuzugreifen

    private val json = Json { ignoreUnknownKeys = true }

    fun loadQuestionnaire(): List<Question> {
        val jsonString = context.assets
            .open("questionnaire.json")
            .bufferedReader()
            .use { it.readText() }

        return json.decodeFromString<List<Question>>(jsonString)
    }
}