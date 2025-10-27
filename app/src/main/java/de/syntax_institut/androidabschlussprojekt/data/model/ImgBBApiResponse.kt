package de.syntax_institut.androidabschlussprojekt.data.model

data class ImgBBApiResponse(
    val data: ImgBBData?,
    val success: Boolean,
    val status: Int
)

data class ImgBBData(
    val id: String,
    val url: String,
    val delete_url: String
)
