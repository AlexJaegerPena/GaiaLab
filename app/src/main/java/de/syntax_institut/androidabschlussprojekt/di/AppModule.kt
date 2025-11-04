package de.syntax_institut.androidabschlussprojekt.di

import com.google.firebase.firestore.FirebaseFirestore
import de.syntax_institut.androidabschlussprojekt.data.remote.IMGBBAPI
import de.syntax_institut.androidabschlussprojekt.data.remote.ImgBBApiService
import de.syntax_institut.androidabschlussprojekt.data.remote.MYAPI
import de.syntax_institut.androidabschlussprojekt.data.remote.SPECIESAPI
import de.syntax_institut.androidabschlussprojekt.data.repository.local.CO2QuizRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.api.IdentifySpeciesRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.api.ImgBBAPIRespository
import de.syntax_institut.androidabschlussprojekt.data.repository.api.MyAPIRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CollectedSpeciesRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.CO2QuizResultRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavFactRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.FavTipRepository
import de.syntax_institut.androidabschlussprojekt.data.repository.firestore.UserRepository
import de.syntax_institut.androidabschlussprojekt.service.AuthService
import de.syntax_institut.androidabschlussprojekt.ui.authentication.AuthViewModel
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz.CO2QuizViewModel
import de.syntax_institut.androidabschlussprojekt.ui.TipsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.identification.IdentifySpeciesViewModel
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection.CollectedSpeciesViewModel
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz.CO2QuizResultViewModel
import de.syntax_institut.androidabschlussprojekt.ui.FactsViewModel
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.favorites.FavFactViewModel
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.favorites.FavTipViewModel
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.ImgBBViewModel
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    // ----- Species API -----
    single { IdentifySpeciesRepository(SPECIESAPI.service) }

    viewModelOf(:: IdentifySpeciesViewModel)


    // ----- CO2Quiz -----
    single { CO2QuizRepository(androidContext()) }
    single { CO2QuizResultRepository(FirebaseFirestore.getInstance()) }

    viewModelOf(::CO2QuizViewModel)
    viewModelOf(::CO2QuizResultViewModel)
    //viewModel { CO2QuizViewModel(get()) }



    // ----- My API -----
    single { MyAPIRepository(MYAPI.service) }

    viewModelOf(:: TipsViewModel)
    viewModelOf(:: FactsViewModel)


    // ----- Firebase -----
    single { AuthService() }
    single { UserRepository(FirebaseFirestore.getInstance()) }
    single { CollectedSpeciesRepository(FirebaseFirestore.getInstance()) }
    single { FavFactRepository(FirebaseFirestore.getInstance()) }
    single { FavTipRepository(FirebaseFirestore.getInstance()) }
    single { CO2QuizResultRepository(FirebaseFirestore.getInstance()) }

    viewModelOf(::AuthViewModel)
    viewModelOf(::UserViewModel)
    viewModelOf(::CollectedSpeciesViewModel)
    viewModelOf(::FavFactViewModel)
    viewModelOf(::FavTipViewModel)
    viewModelOf(::CO2QuizResultViewModel)


    // ----- ImgBB API -----
    single<ImgBBApiService> { IMGBBAPI.service }
    single { ImgBBAPIRespository(get(), androidContext()) }

    // single { ImgBBAPIRespository(IMGBBAPI.service, androidContext()) }

    viewModelOf(::ImgBBViewModel)
}