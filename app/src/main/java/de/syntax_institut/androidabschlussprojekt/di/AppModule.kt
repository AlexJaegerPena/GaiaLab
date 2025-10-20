package de.syntax_institut.androidabschlussprojekt.di

import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.remote.MYAPI
import de.syntax_institut.androidabschlussprojekt.data.remote.SPECIESAPI
import de.syntax_institut.androidabschlussprojekt.data.repository.local.CO2QuizRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.api.IdentifySpeciesRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.api.MyAPIRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CollectedSpeciesRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CO2QuizResultRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavFactRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavTipRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.UserRepository
import de.syntax_institut.androidabschlussprojekt.service.AuthService
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.climateFacts.ClimateFactsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz.CO2QuizViewModel
import de.syntax_institut.androidabschlussprojekt.ui.ecoLab.ecoFacts.EcoFactsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.ecoLab.ecoTips.EcoTipsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesFacts.SpeciesFactsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesIdent.IdentifySpeciesViewModel
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.CollectedSpeciesViewModel
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.FavFactsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    // ----- Species -----
    single { IdentifySpeciesRepository(SPECIESAPI.service) }

    viewModelOf(:: IdentifySpeciesViewModel)


    // ----- Questionnaire -----
    single { CO2QuizRepository(androidContext()) }

    viewModelOf(::CO2QuizViewModel)


    // ----- My API -----
    single { MyAPIRepository(MYAPI.service) }

    //viewModelOf(:: SpeciesFactsViewModel)
    viewModel {
        SpeciesFactsViewModel(get())
    }

    // viewModelOf(:: ClimateFactsViewModel)
    viewModel {
        ClimateFactsViewModel(get())
    }

    // viewModelOf(:: EcoFactsViewModel)
    viewModel {
        EcoFactsViewModel(get())
    }

    // viewModelOf(:: EcoTipsViewModel)
    viewModel {
        EcoTipsViewModel(get())
    }

    // ----- Firebase -----
    single { AuthService() }
    single { UserRepository(FirebaseFirestore.getInstance()) }
    single { CollectedSpeciesRepository(FirebaseFirestore.getInstance()) }
    single { FavFactRepository(FirebaseFirestore.getInstance()) }
    single { FavTipRepository(FirebaseFirestore.getInstance()) }
    single { CO2QuizResultRepository(FirebaseFirestore.getInstance()) }

    viewModelOf(:: AuthViewModel)
    viewModelOf(::UserViewModel)
    viewModelOf(::CollectedSpeciesViewModel)
    viewModelOf(::FavFactsViewModel)
    viewModelOf(::CO2QuizViewModel)


}