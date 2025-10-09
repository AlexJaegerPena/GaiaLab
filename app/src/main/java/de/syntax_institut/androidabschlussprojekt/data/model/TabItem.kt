package de.syntax_institut.androidabschlussprojekt.data.model

import de.syntax_institut.androidabschlussprojekt.R

data class TabItem(
    val name: String,
    val icon: Int
)

val tabs: List<TabItem> = listOf(
    TabItem("Species Lab", R.drawable.ic_launcher_foreground),
    TabItem("Climate Zone", R.drawable.ic_launcher_foreground),
    TabItem("Eco Hub", R.drawable.ic_launcher_foreground),
)