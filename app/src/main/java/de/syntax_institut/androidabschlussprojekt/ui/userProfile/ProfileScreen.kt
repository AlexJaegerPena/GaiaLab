package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import android.R.attr.bottom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.CustomTextField
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    authVM: AuthViewModel = koinViewModel(),
    userVM: UserViewModel = koinViewModel(),
    onPopUpBackStack: () -> Unit,
    onShowCO2Result: () -> Unit,
    onShowSpecies: () -> Unit,
    onShowFavFacts: () -> Unit,
    onShowFavTips: () -> Unit
) {

    val username by userVM.username.collectAsState()

    var newUsername by remember(username) {mutableStateOf(username)} // remember(username) um namen zu aktualisieren

    var enabled by remember { mutableStateOf(false)}
    val hazeState = remember { HazeState() }

    FullScreenBox(
        bgImage = R.drawable.bg_profile,
        onClick = { onPopUpBackStack() },
        showSecondButton = true,
        secondButtonIcon = Icons.AutoMirrored.Filled.Logout,
        secondButtonText = "Exit",
        buttonTopPadding = 55.dp,
        onSecondButtonClick = { authVM.logout() }
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp)
                .padding(top = 180.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("${username}'s log".uppercase(),
                style = MyTypography.titleLarge,
                color = CardContent
            )
            Column(modifier = modifier) {
                CustomTextField(
                    modifier = modifier,
                    value = newUsername,
                    enabled = enabled,
                    onValueChange = { newUsername = it },
                    leadingIcon = Icons.Default.Person,
                    trailingIcon = if (enabled) Icons.Default.Check else Icons.Default.Edit,
                    onTrailingIconClick = {
                        if (enabled) {
                            userVM.updateUsername(newUsername)
                        }
                        enabled = !enabled
                    },
                    placeholder = { "Name" },
                    hazeState = hazeState,
                    )
            }
            Spacer(modifier = Modifier.height(20.dp))
            ProfileGrid(
                hazeState = hazeState,
                onShowCO2Result = { onShowCO2Result()},
                onShowSpecies = {onShowSpecies()},
                onShowFavFacts = { onShowFavFacts()},
                onShowFavTips = { onShowFavTips()}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        onPopUpBackStack = {},
        onShowCO2Result = {},
        onShowSpecies = {},
        onShowFavFacts = {},
        onShowFavTips = {},
        userVM = viewModel()
    )
}