package de.syntax_institut.androidabschlussprojekt.ui.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    userName: String
) {

    val welcomeText = "Welcome ${userName}"

    FullScreenBox(
        modifier = modifier,
        bgImage = R.drawable.bg_authgranted,
        showButton = false,
    ) {
        Column(modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(welcomeText,
                color = CardContent,
                style = MyTypography.headlineLarge
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
        userName = "Explorer"
    )
}