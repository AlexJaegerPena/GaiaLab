package de.syntax_institut.androidabschlussprojekt.ui.common.bottomBar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.TabItem
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardCategoryText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

@Composable
fun GlassmorphicBottomBar(
    hazeState: HazeState,
    selectedTab: TabItem,
    onTabSelected: (TabItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = TabItem.entries
    val selectedTabIndex = tabs.indexOf(selectedTab)

    val shape = RoundedCornerShape(18.dp)

    Box(
        modifier = modifier
            .padding(vertical = 24.dp, horizontal = 40.dp)
            .fillMaxWidth()
            .height(64.dp)
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
    ) {
        BottomBarTabs(
            tabs = tabs,
            selectedTab = selectedTabIndex,
            onTabSelected = onTabSelected
        )

        val animatedSelectedTabIndex by animateFloatAsState(
            targetValue = selectedTabIndex.toFloat(),
            label = "animatedSelectedTabIndex",
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
                dampingRatio = Spring.DampingRatioLowBouncy,
            )
        )

        val animatedColor by animateColorAsState(
            targetValue = getTabColor(selectedTab),
            label = "animatedColor",
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
            )
        )

        // Glowing Circle Background
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        ) {
            val tabWidth = size.width / tabs.size
            drawCircle(
                color = animatedColor.copy(alpha = .6f),
                radius = size.height / 2,
                center = Offset(
                    (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                    size.height / 2
                )
            )
        }

        // Animated Border Accent
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
        ) {
            val corner = 18.dp.toPx()
            val path = Path().apply {
                addRoundRect(RoundRect(size.toRect(), CornerRadius(corner, corner)))
            }
            val length = PathMeasure().apply { setPath(path, false) }.length

            val tabWidth = size.width / tabs.size
            drawPath(
                path,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        animatedColor.copy(alpha = 0f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 0f),
                    ),
                    startX = tabWidth * animatedSelectedTabIndex,
                    endX = tabWidth * (animatedSelectedTabIndex + 1),
                ),
                style = Stroke(
                    width = 6f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(length / 2, length)
                    )
                )
            )
        }
    }
}