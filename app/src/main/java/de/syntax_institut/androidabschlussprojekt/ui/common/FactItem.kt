package de.syntax_institut.androidabschlussprojekt.ui.common

import android.R.attr.top
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.ui.theme.FactCardBg
import de.syntax_institut.androidabschlussprojekt.ui.theme.FactCardText
import de.syntax_institut.androidabschlussprojekt.ui.theme.factCardGradient
import de.syntax_institut.androidabschlussprojekt.util.neonCyanBorder
import kotlin.math.absoluteValue


@Composable
fun FactItem(
    modifier: Modifier = Modifier,
    fact: Fact,
    pagerState: PagerState,
    page: Int
) {
    Card(modifier = Modifier

        .graphicsLayer {
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).absoluteValue
            val scale = 1f - pageOffset * 0.1f
            scaleX = scale
            scaleY = scale
            alpha = 1f - pageOffset * 0.2f
        }
        .neonCyanBorder()
        .fillMaxWidth()
        .height(500.dp)
        .clip(RoundedCornerShape(12.dp))

        .aspectRatio(0.5f),

        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier
            .background(FactCardBg)
            .fillMaxSize()
            .padding(top = 100.dp)
            .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FactImage(fact = fact)
            Text(
                text = fact.title,
                fontWeight = FontWeight.Bold,
                color = FactCardText
            )
            Text(fact.text,
                color = FactCardText)
            Text(fact.category,
                color = FactCardText)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FactItemPreview() {
    FactItem(
        fact = Fact(
            id = 1,
            title = "test",
            text = "testealsdkjnsfdkj sdlkfjl slkdfjlksjdflk lskdjflk sdfklölsdjkfösdjkdf lkdjfölsjdf sjdflösdjfl lkdjkf djfjl  dldjlfk",
            category = "animal",
            imageUrl = painterResource(id = R.drawable.bg_home).toString()
        ),
       pagerState = {} as PagerState,
       page = 1
    )
}
