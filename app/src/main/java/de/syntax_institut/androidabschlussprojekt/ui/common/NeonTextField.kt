package de.syntax_institut.androidabschlussprojekt.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.util.cardImageBorder


@Composable
fun NeonTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = modifier.cardImageBorder(),
        // colors =
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            leadingIcon?.let {
                Icon(imageVector = it, contentDescription = null, tint = CardContent)
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                IconButton(onClick = { onTrailingIconClick?.invoke() }) {
                    Icon(imageVector = it, contentDescription = null, tint = CardContent)
                }
            }
        },
        placeholder = placeholder
    )
}


