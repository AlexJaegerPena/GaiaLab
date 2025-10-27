package de.syntax_institut.androidabschlussprojekt.ui.common.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardCategoryText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild


@Composable
fun CardButtonBar(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    onNavigateBack: () -> Unit,
    onFavClick: () -> Unit,
    onNavigateForward: () -> Unit,
    isFavorite: Boolean
) {
    val shape = RoundedCornerShape(18.dp)

    Box(modifier = modifier) {

        // blurred glow background
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
                .blur(40.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        ) {
            drawRoundRect(
                color = Color.White.copy(alpha = 0.03f),
                cornerRadius = CornerRadius(18.dp.toPx(), 18.dp.toPx())
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp)
                .padding(bottom = 140.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardButton(
                modifier = Modifier
                    .height(50.dp)
                    .width(64.dp),
                buttonIcon = Icons.Default.ArrowBackIosNew,
                onClick = { onNavigateBack() }
            )

            CardButton(
                modifier = Modifier
                    .height(50.dp)
                    .width(64.dp),
                buttonIcon = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                onClick = { onFavClick() }
            )

            CardButton(
                modifier = Modifier
                    .height(50.dp)
                    .width(64.dp),
                buttonIcon = Icons.AutoMirrored.Filled.ArrowForwardIos,
                onClick = { onNavigateForward() }
            )
        }
    }
}





@Preview(showBackground = true)
@Composable
fun CardButtonBarPreview() {
    CardButtonBar(
        hazeState = HazeState(),
        onNavigateBack = {},
        onFavClick = {},
        onNavigateForward = {},
        isFavorite = true
    )
}