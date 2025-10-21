package de.syntax_institut.androidabschlussprojekt.data.model


data class SpeciesApiResponse(
    val predictions: List<Prediction>
)


data class Prediction(
    val region_group_id: String,
    val taxa: Taxa
)


data class Taxa(
    val items: List<TaxonItem>,
    val type: String
)


data class TaxonItem(
    val probability: Double,
    val scientific_name: String,
    val scientific_name_id: String
)

// TODO: name ändern
