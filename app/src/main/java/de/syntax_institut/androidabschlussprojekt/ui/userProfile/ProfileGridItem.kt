package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import android.R.attr.enabled
import android.R.attr.radius
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.ui.theme.ButtonContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardCategoryText
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import kotlinx.coroutines.delay


@Composable
fun ProfileGridItem(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    buttonIcon: ImageVector?,
    buttonText: String?,
    onClick: () -> Unit
) {

    var isClicked by remember { mutableStateOf(false) }

    val glowColor = Color(0xFF79ADA9)
    val shape = RoundedCornerShape(18.dp)


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
            .background(color = Color.Black.copy(alpha = 0.2f), shape = shape)
            .scale(scale)
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
            )
            .clickable(
                onClick = { isClicked = true }
            ),
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
                    color =Color(0xFF649C9C).copy(alpha = 0.3f),
                    radius = size.width / 2,
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
                    endX = size.width + 4,
                ),
                style = Stroke(
                    width = 12f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(length / 2, length)
                    )
                )
            )
        }

        Column(modifier = Modifier
            .padding(10.dp)
            .aspectRatio(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            buttonIcon?.let {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = it,
                    contentDescription = null,
                    tint = ButtonContent
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            buttonText?.let {
                Text(
                    text = buttonText,
                    style = MyTypography.bodyLarge,
                    color = ButtonContent
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileGridItemPreview() {
    ProfileGridItem(
        hazeState = HazeState(),
        buttonIcon = Icons.Default.Add,
        buttonText = "Test",
        onClick = {}
    )
}

