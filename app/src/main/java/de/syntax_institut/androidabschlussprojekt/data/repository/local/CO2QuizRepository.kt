package de.syntax_institut.androidabschlussprojekt.data.repository.local

import android.content.Context
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.Question
import kotlinx.serialization.json.Json

class CO2QuizRepository(private val context: Context) { // context um auf assets zuzugreifen

    private val json = Json { ignoreUnknownKeys = true }

    fun loadCO2Quiz(): List<Question> {
        val jsonString = context.assets
            .open("co2quiz.json")
            .bufferedReader()
            .use { it.readText() }

        return json.decodeFromString<List<Question>>(jsonString)
    }
}