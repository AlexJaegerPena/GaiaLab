package de.syntax_institut.androidabschlussprojekt.ui.userProfile.favorites

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.SharedItemData
import de.syntax_institut.androidabschlussprojekt.ui.common.GlassmorphicBorder
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import dev.chrisbanes.haze.HazeState
import kotlinx.coroutines.delay


@Composable
fun <T: SharedItemData> FavListItem(
    modifier: Modifier = Modifier,
    item: T,
    hazeState: HazeState,
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

    GlassmorphicBorder(
        hazeState = hazeState,
        glowColor = Color(0xFF326A66).copy(alpha = 1f),
        modifier = Modifier.scale(scale)
    ) {
        Row(modifier = modifier
            .height(100.dp)
            .clickable(onClick = { isClicked = true } )
        ) {
            AsyncImage(
                model = item.imageUrl,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .padding(2.dp),
                contentScale = ContentScale.Crop,
                contentDescription = "Image for ${item.title}",
                colorFilter = ColorFilter.tint(color = Color.Black.copy(alpha = 0.2f), blendMode = BlendMode.Darken),
                placeholder = painterResource(id = R.drawable.placeholder_image),
                error = painterResource(id = R.drawable.placeholder_image)
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(item.title,
                    style = MyTypography.titleSmall,
                    color = CardContent,
                )
                Text(item.text,
                    style = MyTypography.bodySmall,
                    color = CardContent,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FavListItemPreview() {
    FavListItem(
        item = Fact(1, "text", "text", "category", "url", "url"),
        hazeState = HazeState(),
        onClick = {}
    )
}