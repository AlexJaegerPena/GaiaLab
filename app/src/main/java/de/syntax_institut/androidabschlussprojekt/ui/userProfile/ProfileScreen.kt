package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    userVM: UserViewModel = koinViewModel()
) {
    FullScreenBox(bgImage = R.drawable.bg_climatetips) {

    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}