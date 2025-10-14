package de.syntax_institut.androidabschlussprojekt.data.model.myApi


data class Fact(
    val id: Int,
    val title: String,
    val text: String,
    val category: String,
    val imageUrl: String? = null
)