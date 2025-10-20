package de.syntax_institut.androidabschlussprojekt.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardText
import de.syntax_institut.androidabschlussprojekt.util.neonCyanBorder


@Composable
fun NeonTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    visualTransformation: PasswordVisualTransformation
) {
    OutlinedTextField(
        modifier = modifier.neonCyanBorder(),
        // colors =
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            leadingIcon?.let {
                Icon(imageVector = it, contentDescription = null, tint = CardText)
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                IconButton(onClick = { onTrailingIconClick?.invoke() }) {
                    Icon(imageVector = it, contentDescription = null, tint = CardText)
                }
            }
        },
        placeholder = placeholder
    )
}


