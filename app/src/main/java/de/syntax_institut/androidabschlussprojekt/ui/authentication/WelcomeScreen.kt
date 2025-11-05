package de.syntax_institut.androidabschlussprojekt.ui.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.UserViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    username: String
) {

  //  val username = userVM.username.collectAsState()

    val welcomeText = "Welcome,\n${username}!".uppercase()

    FullScreenBox(
        modifier = modifier,
        bgImage = R.drawable.bg_authgranted,
        showButton = false,
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(welcomeText,
                color = CardContent,
                style = MyTypography.headlineMedium,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
     username = "Explorer"
    )
}