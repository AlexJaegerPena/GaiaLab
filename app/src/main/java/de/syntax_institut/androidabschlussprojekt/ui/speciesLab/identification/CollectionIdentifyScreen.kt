package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.identification

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection.CollectedSpeciesViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection.CollectionGrid
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection.DetailDialog
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun CollectionIdentifyScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
) {

    val hazeState = remember { HazeState() }

    FullScreenBox(
        modifier = modifier,
        bgImage = R.drawable.bg_speciescollection,
        buttonTopPadding = 40.dp,
        onClick = { onPopUpBackStack() }
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            CollectionGrid(
                modifier = Modifier,
                hazeState = hazeState
            )
            Spacer(modifier = Modifier.height(43.dp))

            PhotoIdentification(hazeState = hazeState )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CollectionIdentifyScreenPreview() {
    CollectionIdentifyScreen(
        onPopUpBackStack = {}
    )
}