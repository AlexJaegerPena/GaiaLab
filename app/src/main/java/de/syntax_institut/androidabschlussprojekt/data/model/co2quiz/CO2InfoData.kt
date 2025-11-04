package de.syntax_institut.androidabschlussprojekt.data.model.co2quiz

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Public
import androidx.compose.ui.graphics.vector.ImageVector

data class CO2InfoData (
    val icon: ImageVector,
    val text: String
)

val infoRowContent: List<CO2InfoData> = listOf(
    CO2InfoData(Icons.Outlined.Public, "The average global CO₂ footprint per person is approximately 6.4 tons per year."),
    CO2InfoData(Icons.Outlined.LocationOn, "In Germany, the average CO₂ footprint per person is around 10.4 tons per year."),
    CO2InfoData(Icons.AutoMirrored.Outlined.TrendingUp, "Qatar has the highest per capita CO₂ footprint worldwide, reaching up to 38.8 tons per year."),
    CO2InfoData(Icons.AutoMirrored.Filled.TrendingDown, "Countries like Somalia and the Democratic Republic of Congo have the lowest CO₂ footprints, close to 0 tons per person per year."),
    CO2InfoData(Icons.Outlined.Info, "A person's CO₂ footprint includes:\n" +
            "• Direct emissions: heating, driving, electricity use\n" +
            "• Indirect emissions: production of goods, infrastructure, public transport, energy generation\n" +
            "• Imported emissions: CO₂ from goods produced abroad and consumed locally")
)