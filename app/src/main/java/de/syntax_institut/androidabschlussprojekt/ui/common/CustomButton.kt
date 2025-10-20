package de.syntax_institut.androidabschlussprojekt.ui.common


import android.R.attr.onClick
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiNature
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardCategoryText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import kotlinx.coroutines.delay


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    buttonIcon: ImageVector?,
    buttonText: String?,
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
            .wrapContentWidth()
            .scale(scale)
            .height(64.dp)
            .padding(10.dp)
            .hazeChild(state = hazeState, shape = shape)
            .border(
                width = Dp.Hairline,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        CardCategoryText.copy(alpha = .8f),
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
                color = glowColor.copy(alpha = 0.3f),
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

        Row(modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            buttonIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = CardCategoryText
                )
            }
            buttonText?.let {
                Text(
                    text = buttonText,
                    color = CardCategoryText
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    CustomButton(
        hazeState = HazeState(),
        onClick = {},
        buttonIcon = Icons.Default.EmojiNature,
        buttonText = "Testbutton"
    )
}