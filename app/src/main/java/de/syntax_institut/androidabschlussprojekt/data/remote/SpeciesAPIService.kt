package de.syntax_institut.androidabschlussprojekt.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntax_institut.androidabschlussprojekt.data.model.SpeciesApiResponse
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import kotlin.jvm.java


interface SpeciesAPIService {

    @Multipart
    @POST("observation/identify")
    suspend fun identifySpecies(
        @Part image: MultipartBody.Part
    ): Response<SpeciesApiResponse>
}


object SPECIESAPI {
    private const val BASE_URL = "https://multi-source.identify.biodiversityanalysis.eu/v2/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    val service: SpeciesAPIService by lazy { retrofit.create(SpeciesAPIService::class.java)}
}
