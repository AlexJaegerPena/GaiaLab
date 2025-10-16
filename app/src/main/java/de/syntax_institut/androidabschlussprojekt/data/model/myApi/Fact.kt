package de.syntax_institut.androidabschlussprojekt.data.model.myApi


data class Fact (
    override val id: Int,
    override val title: String,
    override val text: String,
    override val category: String,
    override val imageUrl: String? = null,
    override val infoUrl: String
) : CardContent