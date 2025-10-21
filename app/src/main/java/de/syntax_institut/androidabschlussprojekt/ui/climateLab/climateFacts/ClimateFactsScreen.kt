package de.syntax_institut.androidabschlussprojekt.ui.climateLab.climateFacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.card.CardItem
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox
import org.koin.androidx.compose.koinViewModel


@Composable
fun ClimateFactsScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
    climateFactsVM: ClimateFactsViewModel = koinViewModel()
) {
    val facts = climateFactsVM.facts.collectAsState().value.shuffled()
    val pagerState = rememberPagerState(pageCount = { facts.count() })

    FullScreenBox(
        bgImage = R.drawable.bg_climatefacts,
        alpha = 1f,
        onClick = { onPopUpBackStack() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(49.dp),
                pageSpacing = 16.dp
            ) { page ->
                CardItem(
                    data = facts[page],
                    pagerState = pagerState,
                    page = page,
                    isFavorite = false // TODO
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ClimateFactsScreenPreview() {
    ClimateFactsScreen(onPopUpBackStack = {})
}