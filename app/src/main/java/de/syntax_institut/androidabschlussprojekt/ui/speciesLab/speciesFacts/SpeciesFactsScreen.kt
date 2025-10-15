package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesFacts

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
import de.syntax_institut.androidabschlussprojekt.ui.common.FactItem
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox
import org.koin.androidx.compose.koinViewModel


@Composable
fun SpeciesFactsScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
    speciesFactsVM: SpeciesFactsViewModel = koinViewModel()
) {
    val facts = speciesFactsVM.facts.collectAsState().value
    val pagerState = rememberPagerState(pageCount = { facts.count() })

    FullScreenBox(
        bgImage = R.drawable.bg_specieslab,
        alpha = 0.5f,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { onPopUpBackStack()} ) {
                Text("Zurück")
            }
            Text("Species Facts")

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(48.dp),
                pageSpacing = 16.dp
            ) { page ->
                FactItem(
                    fact = facts[page],
                    pagerState = pagerState,
                    page = page
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SpeciesFactsScreenPreview() {
    SpeciesFactsScreen(onPopUpBackStack = { } )
}