package de.syntax_institut.androidabschlussprojekt.ui.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.User
import de.syntax_institut.androidabschlussprojekt.ui.common.card.CustomButton
import de.syntax_institut.androidabschlussprojekt.ui.common.NeonTextField
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.UserViewModel
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    authVM: AuthViewModel = koinViewModel(),
    userVM: UserViewModel = koinViewModel()
) {

    val email = authVM?.email?.collectAsState()?.value ?: ""
    val password = authVM?.password?.collectAsState()?.value ?: ""

    var showRegister by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false)}
    val authButtonText = if (showRegister) "Register" else "Login"
    val infoText = if (showRegister) "Already have an account?" else "No account? Register first."

    val hazeState = remember { HazeState() }


    FullScreenBox(
        modifier = Modifier,
        bgImage = R.drawable.bg_home
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            NeonTextField(
                modifier = modifier,
                value = email,
                onValueChange = { authVM.onEmailInput(it)},
                leadingIcon = Icons.Default.Email,
                trailingIcon = Icons.Default.Cancel,
                onTrailingIconClick = {  } ,
                placeholder = { Text("Email") }

            )
            NeonTextField(
                modifier = modifier,
                value = password,
                onValueChange = { authVM.onPasswordInput(it)},
                leadingIcon = Icons.Default.Lock,
                trailingIcon = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                onTrailingIconClick = { showPassword = !showPassword },
                placeholder = { Text("Password") }
            )
            Text(infoText,
                modifier = Modifier.clickable(onClick = { showRegister = !showRegister })
            )

            CustomButton(
                hazeState = hazeState,
                buttonIcon = Icons.Default.AcUnit,
                buttonText = authButtonText,
                onClick = {
                    if (showRegister) {
                        authVM.registerAndSaveUser(userVM)
                        val newFirebaseUser = authVM.currentUser.value
                        newFirebaseUser?.let {
                            val newUser = User(
                                userId =it.uid,
                                username = "",
                                email = it.email
                            )
                            userVM.saveUser(newUser)
                        }
                    } else {
                        authVM.login()
                    }


                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}