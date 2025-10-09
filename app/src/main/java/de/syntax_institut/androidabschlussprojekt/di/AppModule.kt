package de.syntax_institut.androidabschlussprojekt.di

import de.syntax_institut.androidabschlussprojekt.data.remote.SPECIESAPI
import de.syntax_institut.androidabschlussprojekt.data.repository.SpeciesRepository
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.SpeciesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { SpeciesRepository(SPECIESAPI.retrofitService) }

    viewModel { SpeciesViewModel(get()) } // get liefert repo

}