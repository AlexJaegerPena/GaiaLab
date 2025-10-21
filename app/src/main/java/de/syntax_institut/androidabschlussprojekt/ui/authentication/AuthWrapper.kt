package de.syntax_institut.androidabschlussprojekt.ui.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.AppStart
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthWrapper(
    modifier: Modifier = Modifier,
    authVM: AuthViewModel = koinViewModel()
) {
    val fireUser = authVM.currentUser.collectAsState().value

    if (fireUser != null) {
        AppStart(modifier)
    } else (
        AuthScreen(modifier)
    )
}


@Preview(showBackground = true)
@Composable
fun AuthWrapperPreview() {
    AuthWrapper()
}