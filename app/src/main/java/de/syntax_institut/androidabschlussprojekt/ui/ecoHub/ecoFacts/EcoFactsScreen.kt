package de.syntax_institut.androidabschlussprojekt.ui.ecoHub.ecoFacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import de.syntax_institut.androidabschlussprojekt.ui.common.CardItem
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox
import org.koin.androidx.compose.koinViewModel


@Composable
fun EcoFactsScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
    ecoFactsVM: EcoFactsViewModel = koinViewModel()

) {
    val facts = ecoFactsVM.facts.collectAsState().value
    val pagerState = rememberPagerState(pageCount = { facts.count() })

    FullScreenBox(
        bgImage = R.drawable.bg_ecofactsscreen,
        alpha = 1f
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Button(onClick = { onPopUpBackStack()} ) {
                Text("Zurück")
            }

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(49.dp),
                pageSpacing = 16.dp
            ) { page ->
                CardItem(
                    data = facts[page],
                    pagerState = pagerState,
                    page = page
                )
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onPopUpBackStack()} ) {
            Text("back")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EcoFactsScreenPreview() {
    EcoFactsScreen(onPopUpBackStack = {})
}