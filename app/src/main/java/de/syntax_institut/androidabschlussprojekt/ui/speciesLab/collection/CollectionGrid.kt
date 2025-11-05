package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection

import android.R.attr.top
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection.CollectedSpeciesViewModel
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun CollectionGrid(
    modifier: Modifier = Modifier,
    collectedSpeciesVM: CollectedSpeciesViewModel = koinViewModel(),
    hazeState: HazeState
) {

    val collectedSpecies = collectedSpeciesVM.collectedSpecies.collectAsState()

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .height(515.dp)
        ) {
            item{}
            item{}
            items(collectedSpecies.value ) { species ->
                CollectionItem(species = species, hazeState = hazeState, collectedSpeciesVM = collectedSpeciesVM)
            }
        }
        HorizontalDivider(
            color = Color(0xFF88B4A3),
            thickness = 3.dp,
            modifier = Modifier.padding(horizontal = 0.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CollectionGridPreview() {
    CollectionGrid(collectedSpeciesVM = viewModel(), hazeState = HazeState())
}