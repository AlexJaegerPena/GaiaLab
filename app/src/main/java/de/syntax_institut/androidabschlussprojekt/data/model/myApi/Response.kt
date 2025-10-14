package de.syntax_institut.androidabschlussprojekt.data.model.myApi


data class Response<T>(
    val statusCode: Int,
    val statusMessage: String,
    val data: T
)