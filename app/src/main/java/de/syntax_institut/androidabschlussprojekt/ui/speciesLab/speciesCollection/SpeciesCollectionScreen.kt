package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesCollection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesFacts.SpeciesFactsViewModel
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox
import org.koin.androidx.compose.koinViewModel


@Composable
fun SpeciesCollectionScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
    speciesFactsVM: SpeciesFactsViewModel = koinViewModel()
) {

    // val speciesCollection
    FullScreenBox(
        bgImage = R.drawable.bg_speciescollection,
        alpha = 0.4f,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = { onPopUpBackStack()} ) {
                    Text("Zurück")
                }
                Text("Species Collection Facts")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SpeciesCollectionPreview() {
    SpeciesCollectionScreen(onPopUpBackStack = {})
}