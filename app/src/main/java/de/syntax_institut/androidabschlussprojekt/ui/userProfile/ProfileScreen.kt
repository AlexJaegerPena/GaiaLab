package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    authVM: AuthViewModel = koinViewModel(),
    userVM: UserViewModel = koinViewModel(),
    onPopUpBackStack: () -> Unit
) {
    FullScreenBox(
        bgImage = R.drawable.bg_climatetips,
        onClick = { onPopUpBackStack() }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(onClick = { authVM.logout() })  {
                Text("Logout")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onPopUpBackStack = {})
}