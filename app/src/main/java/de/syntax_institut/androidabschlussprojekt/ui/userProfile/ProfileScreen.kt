package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.common.NeonTextField


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    authVM: AuthViewModel,
    userVM: UserViewModel,
    onPopUpBackStack: () -> Unit
) {
    val email = authVM.email.collectAsState().value
    val password = authVM.password.collectAsState().value
    val userName = userVM.userName.collectAsState().value
    var error = authVM.error.collectAsState().value

    var readOnly by remember { mutableStateOf(true)}

    FullScreenBox(
        bgImage = R.drawable.bg_profile,
        onClick = { onPopUpBackStack() },
        showSecondButton = true,
        secondButtonIcon = Icons.AutoMirrored.Filled.Logout,
        secondButtonText = "Exit",
        buttonTopPadding = 20.dp,
        onSecondButtonClick = { authVM.logout() }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = modifier) {
                IconButton(
                    onClick = { readOnly = !readOnly }
                ) {
                    Icon(if (readOnly) Icons.Default.Edit else Icons.Default.Check,
                        contentDescription = "edit"
                    )
                }
                NeonTextField(
                    modifier = modifier,
                    value = userName,
                    readOnly = readOnly,
                    onValueChange = { userVM.updateUsername(it)},
                    leadingIcon = Icons.Default.Email,
                    trailingIcon = Icons.Default.Cancel,
                    onTrailingIconClick = {  } ,
                    placeholder = { Text("Explorer name") }

                )
                NeonTextField(
                    modifier = modifier,
                    value = email,
                    readOnly = readOnly,
                    onValueChange = { authVM.updateEmail(it)},
                    leadingIcon = Icons.Default.Email,
                    trailingIcon = Icons.Default.Cancel,
                    onTrailingIconClick = {  } ,
                    placeholder = { Text("Email") }
                )
                NeonTextField(
                    modifier = modifier,
                    value = password,
                    readOnly = true,
                    onValueChange = { authVM.updatePassword(it)},
                    leadingIcon = Icons.Default.Email,
                    trailingIcon = Icons.Default.Cancel,
                    onTrailingIconClick = {  } ,
                    placeholder = { Text("Password") }
                )
                if (error != null) {
                    Text(error)
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(35.dp),
                verticalArrangement = Arrangement.spacedBy(35.dp),
                modifier = Modifier
                    .height(511.dp)) { }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        onPopUpBackStack = {},
        authVM = viewModel(),
        userVM = viewModel()
    )
}