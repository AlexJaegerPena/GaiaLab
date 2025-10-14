package de.syntax_institut.androidabschlussprojekt.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Response
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface MyAPIService {
    @GET("/api/species/facts")
    suspend fun getSpeciesFacts(): Response<List<Fact>>

    @GET("/api/species/facts/random")
    suspend fun getRandomSpeciesFacts(): Response<Fact>


    @GET("/api/climate/facts")
    suspend fun getClimateFacts(): Response<List<Fact>>

    @GET("/api/climate/facts/random")
    suspend fun getRandomClimateFacts(): Response<Fact>

    @GET("/api/climate/tips/")
    suspend fun getClimateTips(): Response<List<Tip>>

    @GET("/api/climate/tips/random")
    suspend fun getClimateTip(): Response<Tip>


    @GET("/api/eco/facts")
    suspend fun getEcoFacts(): Response<List<Fact>>

    @GET("/api/eco/facts/random")
    suspend fun getRandomEcoFacts(): Response<Fact>

    @GET("/api/eco/tips/")
    suspend fun getEcoTips(): Response<List<Tip>>

    @GET("/api/eco/tips/random")
    suspend fun getRandomEcoTip(): Response<Tip>
}

object MYAPI {
    private const val BASE_URL = "http://0.0.0.0:8080/"

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

    val service: MyAPIService by lazy { retrofit.create(MyAPIService::class.java)}
}

