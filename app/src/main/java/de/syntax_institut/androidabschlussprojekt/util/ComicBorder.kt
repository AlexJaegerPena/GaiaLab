package de.syntax_institut.androidabschlussprojekt.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

fun Modifier.comicBorder(
    // strokeWidth: Float = 8f,
    strokeColor: Color = Color.Black,
    bgColor: Color = Color.White
) = this.drawBehind {
    drawRect(color = bgColor, size = size)

    val strokeWidth = 5f
    val path = Path().apply {
        moveTo(0f, 0f)
        quadraticTo(size.width * 0.15f, -10f, size.width, 0f)
        quadraticTo(size.width + 10f, size.height * 0.2f, size.width, size.height)
        quadraticTo(size.width * 0.25f, size.height + 10f, 0f, size.height)
        quadraticTo(-10f, size.height * 0.2f, 0f, 0f)
    }
    drawPath(
        path = path,
        color = strokeColor,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )
}