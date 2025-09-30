package de.syntax_institut.androidabschlussprojekt.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SpeciesApiResponse(
    val predictions: List<Prediction>
)

@Serializable
data class Prediction(
    val region_group_id: String,
    val taxa: Taxa
)

@Serializable
data class Taxa(
    val items: List<TaxonItem>,
    val type: String
)

@Serializable
data class TaxonItem(
    val probability: Double,
    val scientific_name: String,
    val scientific_name_id: String
)
