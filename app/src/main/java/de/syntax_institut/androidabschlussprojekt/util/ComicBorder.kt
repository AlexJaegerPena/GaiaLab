package de.syntax_institut.androidabschlussprojekt.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

fun Modifier.comicBorder(
    strokeWidth: Float = 8f,
    color: Color = Color.Black
) = this.drawBehind {
    val path = Path().apply {
        moveTo(0f, 0f)
        quadraticBezierTo(size.width * 0.15f, -10f, size.width, 0f)
        quadraticBezierTo(size.width + 10f, size.height * 0.2f, size.width, size.height)
        quadraticBezierTo(size.width * 0.25f, size.height + 10f, 0f, size.height)
        quadraticBezierTo(-10f, size.height * 0.2f, 0f, 0f)
    }

    drawPath(
        path = path,
        color = color,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )
}