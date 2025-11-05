package de.syntax_institut.androidabschlussprojekt.ui.userProfile.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.FactsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun FavFactScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
   // onNavigateToDetails: () -> Unit,
    favFactVM: FavFactViewModel = koinViewModel(),
    category: String,
    factVM: FactsViewModel = koinViewModel()
) {

    LaunchedEffect(category) {
        factVM.setCategory(category)
    }

    val allFacts = factVM.facts.collectAsState().value
    val favFacts = favFactVM.favFacts.collectAsState().value

    val favFactDetails = remember(allFacts, favFacts) {
        factVM.getFactsById(favFacts)
    }

    val hazeState = remember { HazeState() }

    if (favFactDetails.isEmpty()) {
        FullScreenBox(bgImage = R.drawable.bg_profile,
            onClick = { onPopUpBackStack() },
            buttonTopPadding = 35.dp
            ) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Lade Daten ...", color = CardContent)
                CircularProgressIndicator(color = CardContent)
            }
        }
        return
    }

    FullScreenBox(
        bgImage = R.drawable.bg_profile,
        onClick = { onPopUpBackStack() },
        buttonTopPadding = 35.dp,
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 60.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("My Favorite Facts".uppercase(),
                style = MyTypography.headlineMedium,
                color = CardContent
            )
            Spacer(modifier = Modifier.height(50.dp))
            LazyColumn(modifier = Modifier.padding(horizontal = 18.dp).height(630.dp)) {
                items(favFactDetails) { fact ->
                    FavListItem(
                        item = fact,
                        hazeState = hazeState,
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FavFactScreenPreview() {
    FavFactScreen(
        onPopUpBackStack = {},
        favFactVM = viewModel(),
        factVM = viewModel(),
        category = "all"
    )
}