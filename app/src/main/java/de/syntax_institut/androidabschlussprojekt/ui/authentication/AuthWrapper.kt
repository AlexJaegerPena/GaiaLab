package de.syntax_institut.androidabschlussprojekt.ui.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.AppStart
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.UserViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthWrapper(
    modifier: Modifier = Modifier,
    authVM: AuthViewModel = koinViewModel(),
    userVM: UserViewModel = koinViewModel(),
) {

    val fireUser = authVM.currentUser.collectAsState().value
    val username = userVM.username.collectAsState().value


    if (fireUser != null) {

        var showAppStart by remember { mutableStateOf(false) }

        LaunchedEffect(fireUser) {
            delay(2000)
            showAppStart = true
        }

        if (showAppStart) {
            AppStart(modifier = modifier)
        } else {
            WelcomeScreen(username = username)
        }

    } else (
        AuthScreen(
            modifier = modifier,
            authVM = authVM,
            userVM = userVM
        )
    )
}


@Preview(showBackground = true)
@Composable
fun AuthWrapperPreview() {
    AuthWrapper()
}