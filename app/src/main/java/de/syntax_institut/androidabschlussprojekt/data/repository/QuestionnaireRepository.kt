package de.syntax_institut.androidabschlussprojekt.data.repository

import android.content.Context
import de.syntax_institut.androidabschlussprojekt.data.model.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuestionnaireRepository(private val context: Context) { // context um auf assets zuzugreifen

    suspend fun loadQuestionnaire(): List<Question> {
        val json = context.assets.open("questionnaire.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Question>>() {}.type
        return Gson().fromJson(json, type)
    }
}