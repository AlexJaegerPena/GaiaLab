package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesFacts

import android.R.attr.bottom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.card.CardButtonBar
import de.syntax_institut.androidabschlussprojekt.ui.common.card.CardItem
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox
import dev.chrisbanes.haze.HazeDefaults.blurRadius
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import org.koin.androidx.compose.koinViewModel


@Composable
fun SpeciesFactsScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
    speciesFactsVM: SpeciesFactsViewModel = koinViewModel()
) {
    val facts = speciesFactsVM.facts.collectAsState().value
    val hazeState = remember { HazeState() }

    if (facts.isEmpty()) {
        FullScreenBox(bgImage = R.drawable.bg_speciesfacts, alpha = 1f) {
            Text("Lade Daten ...", color = Color.White)
        }
        return
    }

    val pagerState = rememberPagerState(pageCount = { facts.size })

    FullScreenBox(
        bgImage = R.drawable.bg_speciesfacts,
        alpha = 1f
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Button(onClick = { onPopUpBackStack()} ) {
                Text("Zurück")
            }

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(top = 51.dp, bottom = 12.dp, start = 49.dp, end = 49.dp),
                pageSpacing = 12.dp
            ) { page ->
                CardItem(
                    data = facts[page],
                    pagerState = pagerState,
                    page = page
                )
            }
            CardButtonBar(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally)
                    .haze(
                        state = hazeState,
                        backgroundColor = MaterialTheme.colorScheme.background,
                        tint = Color.Black.copy(alpha = 1f),
                        blurRadius = 3.dp,
                    ),


                hazeState = hazeState,
                onNavigateBack = {},
                onFavClick = {},
                onNavigateForward = {}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SpeciesFactsScreenPreview() {
    SpeciesFactsScreen(onPopUpBackStack = { } )
}