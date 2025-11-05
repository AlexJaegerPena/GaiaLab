package de.syntax_institut.androidabschlussprojekt.ui.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.User
import de.syntax_institut.androidabschlussprojekt.ui.common.GlassmorphicButton
import de.syntax_institut.androidabschlussprojekt.ui.common.GlassmorphicTextField
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.UserViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import dev.chrisbanes.haze.HazeState


@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    authVM: AuthViewModel,
    userVM: UserViewModel
) {

    val email = authVM.email.collectAsState().value
    val password = authVM.password.collectAsState().value
    var error = authVM.error.collectAsState().value

    var showRegister by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false)}

    val authButtonText = if (showRegister) "Register" else "Enter"
    val infoText = if (showRegister) "Already have an account? Login." else "No account yet? Register first."
    var bgImage = if (error != null ) R.drawable.bg_authdenied else R.drawable.bg_auth

    val hazeState = remember { HazeState() }


    FullScreenBox(
        modifier = Modifier,
        bgImage = bgImage,
        showButton = false
    ) {
        Box() {
            Column(modifier = Modifier.fillMaxSize().padding(top = 400.dp).padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                GlassmorphicTextField(
                    modifier = Modifier.width(260.dp),
                    hazeState = hazeState,
                    value = email,
                    onValueChange = { authVM.updateEmail(it) },
                    leadingIcon = Icons.Default.Email,
                    trailingIcon = Icons.Default.Cancel,
                    onTrailingIconClick = {  } ,
                    placeholder = { Text("Email") }
                )
                GlassmorphicTextField(
                    modifier = Modifier.width(260.dp),
                    hazeState = hazeState,
                    value = password,
                    onValueChange = { authVM.updatePassword(it)},
                    leadingIcon = Icons.Default.Lock,
                    trailingIcon = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    onTrailingIconClick = { showPassword = !showPassword },
                    placeholder = { Text("Password") }
                )

                Text(infoText,
                    style = MyTypography.bodySmall,
                    color = CardContent,
                    modifier = Modifier
                        .padding(top = 9.dp)
                        .clickable(onClick = { showRegister = !showRegister }
                        )
                )
                if (error != null) {
                    Text(error!!,
                        style = MyTypography.bodySmall,
                        color = Color(0xFFC64554),
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(260.dp)
                            .padding(top = 40.dp)
                    )
                }
        }

        Box() {
            if (error != null) {

            } else {
                GlassmorphicButton(
                    modifier = Modifier.padding(start = 365.dp, top = 497.dp).height(80.dp),
                    hazeState = hazeState,
                    buttonIcon = Icons.AutoMirrored.Filled.Login,
                   //  buttonText = authButtonText,
                    buttonText = null,
                    bgColor = Color.Black,
                    bgAlpha = 0.7f,
                    onClick = {
                        if (showRegister) {
                            authVM.registerAndSaveUser(userVM)
                            val newFirebaseUser = authVM.currentUser.value
                            newFirebaseUser?.let {
                                val newUser = User(
                                    userId =it.uid,
                                    username = "Explorer",
                                    email = it.email
                                )
                                userVM.saveUser(newUser)
                                error = null
                            }
                        } else {
                            authVM.login()
                            error = null
                        }
                    }
                )
            }
        }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(
        authVM = viewModel(),
        userVM = viewModel()
    )
}