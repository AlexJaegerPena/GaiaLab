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
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.TipsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun FavTipsScreen(
    onPopUpBackStack: () -> Unit,
    tipsVM: TipsViewModel = koinViewModel(),
    category: String,
    favTipVM: FavTipViewModel = koinViewModel()
) {
    LaunchedEffect(category) {
        tipsVM.getTips(category)
    }

    val allTips = tipsVM.tips.collectAsState().value
    val favTips = favTipVM.favTips.collectAsState().value

    val favTipDetails = remember(allTips, favTips) {
        tipsVM.getTipsById(favTips)
    }

    val hazeState = remember { HazeState() }

    if (favTipDetails.isEmpty()) {
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
            Text("My Favorite Tips".uppercase(),
                style = MyTypography.headlineMedium,
                color = CardContent
            )
            Spacer(modifier = Modifier.height(50.dp))
            LazyColumn(modifier = Modifier
                .padding(horizontal = 18.dp)
                .height(630.dp)
            ) {
                items(favTipDetails) { tip ->
                    FavListItem(
                        item = tip,
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
fun FavTipsScreenPreview() {
    FavTipsScreen(
        onPopUpBackStack = {},
        category ="all"
    )
}