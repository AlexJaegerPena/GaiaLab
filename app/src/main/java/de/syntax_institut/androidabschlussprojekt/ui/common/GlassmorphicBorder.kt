package de.syntax_institut.androidabschlussprojekt.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild


@Composable
fun GlassmorphicBorder(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    shape: Shape = RoundedCornerShape(18.dp),
    glowColor: Color = Color(0xFF6FD2C6),
    enabled: Boolean = true,
    content: @Composable (() -> Unit)
) {

    Box(
        modifier = Modifier
            .background(color = Color.Black.copy(alpha = 0.2f), shape = shape)
            .hazeChild(state = hazeState, shape = shape)
            .border(
                width = Dp.Hairline,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6BC6F1).copy(alpha = .8f),
                        Color(0xFF5DBCCB).copy(alpha = .2f),
                    ),
                ),
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {

        // Glowing background
        if (enabled) {
            Canvas(
                modifier = Modifier
                    .matchParentSize()
                    .clip(shape)
                    .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            ) {
                drawCircle(
                    color = glowColor.copy(alpha = 0.6f),
                    radius = size.width / 3,
                    center = Offset(size.width / 2, size.height / 2)
                )
            }
        }

        // Border glow
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
        ) {
            val corner = 18.dp.toPx()
            val path = Path().apply {
                addRoundRect(RoundRect(size.toRect(), CornerRadius(corner, corner)))
            }
            val length = PathMeasure().apply { setPath(path, false) }.length

            drawPath(
                path,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        glowColor.copy(alpha = 0f),
                        glowColor.copy(alpha = 0.8f),
                        glowColor.copy(alpha = 1f),
                        glowColor.copy(alpha = 0f),
                    ),
                    startX = 0f,
                    endX = size.width + 1,
                ),
                style = Stroke(
                    width = 10f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(length / 2, length)
                    )
                )
            )
        }
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun GlassmorphicBorderPreview() {
    GlassmorphicBorder(
        hazeState = HazeState(),
        content = {}
    )
}