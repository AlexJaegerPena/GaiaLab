package de.syntax_institut.androidabschlussprojekt.ui.climateLab.climateTips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.card.CardButtonBar
import de.syntax_institut.androidabschlussprojekt.ui.common.card.CardItem
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.FavFactViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.FavTipViewModel
import dev.chrisbanes.haze.HazeState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun ClimateTipsScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
    climateTipsVM: ClimateTipsViewModel = koinViewModel(),
    favTipVM: FavTipViewModel = koinViewModel()
) {
    val tips = climateTipsVM.tips.collectAsState().value

    val hazeState = remember { HazeState() }
    val pagerState = rememberPagerState(pageCount = { tips.size })
    val animationScope = rememberCoroutineScope()

    val currentTip = tips.getOrNull(pagerState.currentPage)

    val favIds = favTipVM.favTips.collectAsState().value.map { it.id } // alle ids holen
    val isFavorite = currentTip?.id in favIds

    if (tips.isEmpty()) {
        FullScreenBox(
            bgImage = R.drawable.bg_climatetips,
            alpha = 1f,
            onClick = { onPopUpBackStack() }
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Lade Daten ...", color = Color.White)
                CircularProgressIndicator(color = CardContent)
            }
        }
        return
    }

    FullScreenBox(
        bgImage = R.drawable.bg_climatetips,
        alpha = 1f,
        onClick = { onPopUpBackStack() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 140.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(top = 51.dp, bottom = 12.dp, start = 49.dp, end = 49.dp),
                pageSpacing = 12.dp
            ) { page ->
                CardItem(
                    data = tips[page],
                    pagerState = pagerState,
                    page = page,
                    isFavorite = isFavorite
                )
            }
            CardButtonBar(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally),
               hazeState = hazeState,
                onNavigateBack = {
                    if (pagerState.canScrollBackward) {
                        animationScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                    pagerState.canScrollBackward },
                onFavClick = {
                    currentTip?.let {
                        favTipVM.toggleFavorite(isFavorite = isFavorite, tip = currentTip)
                    }
                },
                onNavigateForward = {
                    if (pagerState.canScrollForward) {
                        animationScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                isFavorite = isFavorite
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ClimateFactsScreenPreview() {
    ClimateTipsScreen(onPopUpBackStack = {})
}