package de.syntax_institut.androidabschlussprojekt.data.model.myApi

interface CardContent {
    val id: Int
    val title: String
    val text: String
    val category: String
    val imageUrl: String?
    val infoUrl: String
}