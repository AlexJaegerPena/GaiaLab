package de.syntax_institut.androidabschlussprojekt.ui.common

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
import de.syntax_institut.androidabschlussprojekt.ui.theme.ButtonContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardCategoryText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import kotlinx.coroutines.delay


@Composable
fun GlassmorphicBackButton(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    shape: Shape = RoundedCornerShape(18.dp),
    glowColor: Color = Color(0xFF6FD2C6),
    onClick: () -> Unit,
) {

    var isClicked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isClicked) 0.9f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scaleAnimation"
    )

    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(120)
            isClicked = false
            onClick()
        }
    }

    Box(
        modifier = modifier
            .scale(scale)
            .hazeChild(state = hazeState, shape = shape)
            .border(
                width = Dp.Hairline,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        ButtonContent.copy(alpha = .8f),
                        CardCategoryText.copy(alpha = .2f),
                    ),
                ),
                shape = shape
            )

            .clickable(onClick = { isClicked = true }),
        contentAlignment = Alignment.Center
    ) {

        // Glowing background
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
                .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        ) {
            drawCircle(
                color = glowColor.copy(alpha = 0.7f),
                radius = size.height / 2,
                center = Offset(size.width / 2, size.height / 2)
            )
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
                    width = 4f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(length / 2, length)
                    )
                )
            )
        }
        Icon(
            modifier = Modifier.padding(5.dp),
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = null,
            tint = ButtonContent
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GlassmorphicBackButtonPreview() {
    GlassmorphicBackButton(
        hazeState = HazeState(),
        onClick = {}
    )
}