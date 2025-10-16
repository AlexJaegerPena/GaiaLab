package de.syntax_institut.androidabschlussprojekt.di

import de.syntax_institut.androidabschlussprojekt.data.remote.MYAPI
import de.syntax_institut.androidabschlussprojekt.data.remote.SPECIESAPI
import de.syntax_institut.androidabschlussprojekt.data.repository.local.QuestionnaireRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.api.IdentifySpeciesRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.api.MyAPIRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CollectedSpeciesRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavFactRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavTipRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.QuestionnaireResultRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.UserRepository
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
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


    // ----- Firebase -----
    single { UserRepository() }
    single { CollectedSpeciesRepository() }
    single { FavFactRepository() }
    single { FavTipRepository() }
    single { QuestionnaireResultRepository() }

   viewModelOf(:: AuthViewModel)


}