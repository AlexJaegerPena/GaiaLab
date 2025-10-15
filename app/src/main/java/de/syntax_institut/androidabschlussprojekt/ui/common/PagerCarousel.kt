package de.syntax_institut.androidabschlussprojekt.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

/*
@Composable
fun <T : ViewModel> PagerCarousel(
    modifier: Modifier = Modifier,
    viewModel: T,
    cardCount: Int,
    content: @Composable (T) -> Unit
) {

    val pagerState = rememberPagerState(pageCount = { cardCount })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 64.dp),
        pageSpacing = 16.dp
    ) { page ->
        FactItem(fact = fact)
    }

}

annotation class T


@Preview(showBackground = true)
@Composable
fun PagerCarouselPreview() {
    PagerCarousel(cardCount = 5)
}

 */