package de.syntax_institut.androidabschlussprojekt.ui.common.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.CardContent
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardCategoryBg
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import kotlin.math.absoluteValue


@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    data: CardContent,
    pagerState: PagerState,
    page: Int,
    isFavorite: Boolean
) {

    Card(modifier = Modifier
        .graphicsLayer {
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).absoluteValue
            val scale = 1f - pageOffset * 0.05f
            scaleX = scale
            scaleY = scale
            alpha = 1f - pageOffset * 0.4f
        }
        .padding(horizontal = 12.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(34.dp)),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Box(modifier = Modifier) {
            Image(
                painterResource(R.drawable.bg_carditem),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(30.dp)),
                contentScale = ContentScale.Crop,
                alpha = 1f
            )
            Column(modifier = Modifier
                .matchParentSize()
                .padding(top = 24.dp)
                .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CardImage(data = data)
                Text(data.title,
                    style = MyTypography.titleMedium,
                    color = CardContent,
                    modifier = modifier
                        .padding(horizontal = 12.dp)
                        .padding(top = 4.dp)
                )
                Text(data.text,
                    style = MyTypography.bodyMedium,
                    color = CardContent,
                    modifier = modifier.padding(horizontal = 12.dp)
                )
            }
            Box(modifier = Modifier
                .height(56.dp)
                .width(271.dp)
                .padding(top = 24.dp, start = 220.dp)
                .background(
                    color = CardCategoryBg,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 0.dp
                    )
                )
            )
            Row(modifier = Modifier
                .align(Alignment.TopStart)
                .matchParentSize()
            ) {
                Text(data.category.replaceFirstChar { it.uppercase() },
                    color = Color.Black.copy(alpha = 0.7f),
                    style = MyTypography.bodySmall,
                    modifier = Modifier
                        .padding(top = 24.dp, start = 17.dp)
                        .background(
                            color = CardCategoryBg,
                            shape = RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 20.dp
                            )
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.padding(top = 26.dp, end = 30.dp),
                    imageVector = if (isFavorite) { Icons.Default.Bookmark } else { Icons.Default.BookmarkBorder },
                    contentDescription = "bookmark",
                    tint = Color.Black.copy(alpha = 0.7f)
                )
            }
            CustomUrlButton(
                url = data.infoUrl,
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 26.dp, end = 26.dp)
            )
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
            text = "Test text",
            category = "animal",
            imageUrl = painterResource(id = R.drawable.bg_home).toString(),
            infoUrl = ""
        ),
       pagerState = {} as PagerState,
       page = 1,
        isFavorite = true
    )
}
