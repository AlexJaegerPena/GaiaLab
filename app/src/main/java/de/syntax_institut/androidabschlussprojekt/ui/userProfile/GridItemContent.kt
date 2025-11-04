package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import androidx.compose.ui.graphics.vector.ImageVector

data class GridItemContent(
    val icon: ImageVector,
    val text: String,
    val onClick: () -> Unit
)

