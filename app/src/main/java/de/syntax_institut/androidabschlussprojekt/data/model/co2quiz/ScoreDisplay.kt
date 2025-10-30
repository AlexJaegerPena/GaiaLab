package de.syntax_institut.androidabschlussprojekt.data.model.co2quiz

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ScoreDisplay (
    val score: Double,
    val evaluation: ScoreEvaluation,
)

enum class ScoreEvaluation(val icon: ImageVector, val text: String, val color: Color) {
    EXCELLENT(Icons.Default.EmojiEvents, "Awesome! You are an Eco Hero!", Color(0xFF3EBA41)),
    GOOD(Icons.Default.Spa, "You are doing great!", Color(0xFF87BF47)),
    FAIR(Icons.AutoMirrored.Filled.TrendingUp, "Keep Going", Color(0xFFFFC107)),
    POOR(Icons.Default.WarningAmber, "Room to Grow", Color(0xFFE55426))
}