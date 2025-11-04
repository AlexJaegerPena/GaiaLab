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
import retrofit2.http.Path


interface MyAPIService {

    @GET("/api/facts/{category}")
    suspend fun getFacts(@Path("category") category: String): Response<List<Fact>>

    @GET("/api/facts/{category}/random")
    suspend fun getRandomFact(@Path("category") category: String): Response<Fact>

    @GET("/api/tips/{category}")
    suspend fun getTips(@Path("category") category: String): Response<List<Tip>>

    @GET("/api/tips/{category}/random")
    suspend fun getRandomTip(@Path("category") category: String): Response<Tip>

}


object MYAPI {
    private const val BASE_URL = "http://10.0.2.2:8080/"

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
