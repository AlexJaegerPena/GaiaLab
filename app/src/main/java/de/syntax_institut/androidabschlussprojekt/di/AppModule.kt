package de.syntax_institut.androidabschlussprojekt.di

import de.syntax_institut.androidabschlussprojekt.data.remote.MYAPI
import de.syntax_institut.androidabschlussprojekt.data.remote.SPECIESAPI
import de.syntax_institut.androidabschlussprojekt.data.repository.QuestionnaireRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.IdentifySpeciesRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.MyAPIRepository
import de.syntax_institut.androidabschlussprojekt.ui.climateZone.climateFacts.ClimateFactsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.climateZone.questionnaire.QuestionnaireViewModel
import de.syntax_institut.androidabschlussprojekt.ui.ecoHub.ecoFacts.EcoFactsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.ecoHub.ecoTips.EcoTipsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesFacts.SpeciesFactsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesIdent.IdentifySpeciesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    // ----- Species -----
    single { IdentifySpeciesRepository(SPECIESAPI.service) }
    // viewModel { IdentifySpeciesViewModel(get()) } // get liefert repo

    viewModelOf(:: IdentifySpeciesViewModel)


    // ----- Questionnaire -----
    single { QuestionnaireRepository(androidContext()) }

    viewModelOf(::QuestionnaireViewModel)


    // ----- My API -----
    single { MyAPIRepository(MYAPI.service) }

    viewModelOf(:: SpeciesFactsViewModel)
    viewModelOf(:: ClimateFactsViewModel)
    viewModelOf(:: EcoFactsViewModel)
    viewModelOf(:: EcoTipsViewModel)

}