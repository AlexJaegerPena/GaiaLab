package de.syntax_institut.androidabschlussprojekt.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.CardContent
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.ui.theme.FactCardText
import de.syntax_institut.androidabschlussprojekt.util.neonCyanBorder
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import kotlin.math.absoluteValue


@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    data: CardContent,
    pagerState: PagerState,
    page: Int
) {

    val hazeState = remember { HazeState() }

    Card(modifier = Modifier
        .graphicsLayer {
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).absoluteValue
            val scale = 1f - pageOffset * 0.05f
            scaleX = scale
            scaleY = scale
            alpha = 1f - pageOffset * 0.2f
        }
        .padding(horizontal = 12.dp)
        .fillMaxWidth()
        // .height(480.dp)
        .clip(RoundedCornerShape(20.dp)),
        //.padding(horizontal = 20.dp),
        //.neonCyanBorder(),
        // .aspectRatio(0.6f),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier
            //.background(color = Color.Black.copy(1f))
        ) {
            Image(
                painterResource(R.drawable.bg_carditem),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(20.dp))
                    .haze(
                        state = hazeState,
                        backgroundColor = MaterialTheme.colorScheme.background,
                        tint = Color.Black.copy(alpha = 0.4f),
                        blurRadius = 30.dp,
                    ),
                contentScale = ContentScale.Crop,
                alpha = 1f
            )
            Column(modifier = Modifier
                .matchParentSize()
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CardImage(data = data)
                Text(
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    color = FactCardText
                )
                Text(data.text,
                    color = FactCardText)
                Text(data.category,
                    color = FactCardText)
                CustomUrlButton(hazeState = hazeState, url = data.infoUrl)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FactItemPreview() {
    CardItem(
        data = Fact(
            id = 1,
            title = "Test Title",
            text = "testealsdkjnsfdkj sdlkfjl slkdfjlksjdflk lskdjflk sdfklölsdjkfösdjkdf lkdjfölsjdf sjdflösdjfl lkdjkf djfjl  dldjlfk",
            category = "animal",
            imageUrl = painterResource(id = R.drawable.bg_home).toString(),
            infoUrl = ""
        ),
       pagerState = {} as PagerState,
       page = 1
    )
}
