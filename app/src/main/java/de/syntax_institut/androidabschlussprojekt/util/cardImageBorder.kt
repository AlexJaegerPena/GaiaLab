package de.syntax_institut.androidabschlussprojekt.util

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.cardImageBorder(
    cornerRadius: Dp = 20.dp,
    borderWidth: Dp = 8.dp,
    glowColor: Color = Color(0xFF71CBD4)
): Modifier {
    return this
        .shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(cornerRadius),
            ambientColor = glowColor.copy(alpha = 0.4f),
            spotColor = glowColor.copy(alpha = 0.4f),
            clip = false
        )
        .drawBehind {
            val gradient = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF66B5AB),
                    Color(0xFF61B19D)
                ),
                start = Offset.Zero,
                end = Offset(size.width, size.height)
            )

            val stroke = Stroke(width = borderWidth.toPx())
            drawRoundRect(
                brush = gradient,
                topLeft = Offset.Zero,
                size = size,
                cornerRadius = CornerRadius(cornerRadius.toPx()),
                style = stroke
            )
        }
        .clip(RoundedCornerShape(cornerRadius))
}

