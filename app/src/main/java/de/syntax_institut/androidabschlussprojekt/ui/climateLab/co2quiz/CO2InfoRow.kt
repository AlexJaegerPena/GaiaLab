package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography


@Composable
fun CO2InfoRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String
) {
    Row(modifier = Modifier.padding(bottom = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CardContent,
            modifier = Modifier.size(30.dp)
        )

        Text(
            text = text,
            color = CardContent,
            style = MyTypography.bodyLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CO2InfoRowPreview() {
    CO2InfoRow(
        icon = Icons.Default.Public,
        text = "Test text"
    )
}