package de.syntax_institut.androidabschlussprojekt.data.model

import de.syntax_institut.androidabschlussprojekt.R

data class TabItem(
    val name: String,
    val icon: Int
)

val tabs: List<TabItem> = listOf(
    TabItem("Explore", R.drawable.ic_launcher_foreground),
    TabItem("Milestones", R.drawable.ic_launcher_foreground),
    TabItem("Tips", R.drawable.ic_launcher_foreground),
)