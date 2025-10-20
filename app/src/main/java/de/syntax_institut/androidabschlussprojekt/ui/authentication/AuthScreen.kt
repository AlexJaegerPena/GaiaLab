package de.syntax_institut.androidabschlussprojekt.ui.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.CustomButton
import de.syntax_institut.androidabschlussprojekt.ui.common.NeonTextField
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardText
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel? = null
) {

    val email = viewModel?.email?.collectAsState()?.value ?: ""
    val password = viewModel?.password?.collectAsState()?.value ?: ""

    val showRegister by remember { mutableStateOf(false) }
    val showPassword by remember { mutableStateOf(false)}
    val authButtonText = if (showRegister) "Register" else "Login"

    val hazeState = remember { HazeState() }


    FullScreenBox(
        modifier = Modifier,
        bgImage = R.drawable.bg_home,
        alpha = 1f
    ) {
        Column(modifier = Modifier) {
            NeonTextField(
                modifier = modifier,
                value = email,
                onValueChange = { },
                leadingIcon = Icons.Default.Email,
                trailingIcon = Icons.Default.Cancel,
                onTrailingIconClick = {  } ,
                placeholder = { Text("Email") },
                visualTransformation = PasswordVisualTransformation()
            )
            NeonTextField(
                modifier = modifier,
                value = password,
                onValueChange = { },
                leadingIcon = Icons.Default.Lock,
                trailingIcon = if (showRegister) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                onTrailingIconClick = { },
                placeholder = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            /*
            NeonTextField(
                modifier = modifier,
                value = email,
                onValueChange = { viewModel.onEmailInput(it) },
                leadingIcon = Icons.Default.Email,
                trailingIcon = Icons.Default.Cancel,
                onTrailingIconClick = { viewModel.onEmailInput("") } ,
                placeholder = { Text("Email") },
                visualTransformation = PasswordVisualTransformation()
            )
            NeonTextField(
                modifier = modifier,
                value = password,
                onValueChange = { viewModel.onPasswordInput(it) },
                leadingIcon = Icons.Default.Lock,
                trailingIcon = if (showRegister) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                onTrailingIconClick = { viewModel.onPasswordInput("") },
                placeholder = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
             */
            CustomButton(
                hazeState = hazeState,
                buttonIcon = Icons.Default.AcUnit,
                buttonText = "Login",
                onClick = {}
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}