package de.syntax_institut.androidabschlussprojekt.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.play.integrity.internal.s
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.UserViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToSpeciesLab: () -> Unit,
    onNavigateToClimateZone: () -> Unit,
    onNavigateToEcoHub: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    FullScreenBox(
        bgImage = R.drawable.bg_home,
        alpha = 1f,
        showButton = false
    ) {
        Box(modifier = Modifier,
            contentAlignment = Alignment.TopCenter) {
            Box(
                modifier = Modifier.padding(top = 40.dp, start = 70.dp)
                    .width(160.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = {onNavigateToClimateZone()})
            )
            Box(
                modifier = Modifier.padding(top = 140.dp, end = 240.dp)
                    .width(120.dp)
                    .height(70.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = {onNavigateToSpeciesLab()})
            )
            Box(
                modifier = Modifier.padding(top = 150.dp, start = 270.dp)
                    .width(100.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = {onNavigateToEcoHub()})
            )
            Box(
                modifier = Modifier.padding(top = 330.dp, start = 10.dp)
                    .width(160.dp)
                    .height(280.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = {onNavigateToProfile()}),
                contentAlignment = Alignment.Center
            ) {
                Text("My discoveries".uppercase(),
                    style = MyTypography.titleLarge,
                    color = CardContent,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToSpeciesLab = {},
        onNavigateToClimateZone = {},
        onNavigateToEcoHub = {},
        onNavigateToProfile = {}
    )
}