package de.syntax_institut.androidabschlussprojekt.data.remote

import com.squareup.moshi.Moshi
import de.syntax_institut.androidabschlussprojekt.BuildConfig
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntax_institut.androidabschlussprojekt.data.model.ImgBBApiResponse
import okhttp3.MultipartBody
import retrofit2.Response
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ImgBBApiService {

    @Multipart
    @POST("1/upload")
    suspend fun uploadImage(
        @Query("key") apiKey: String,
        @Part image: MultipartBody.Part
    ): Response<ImgBBApiResponse>
}


object IMGBBAPI {

    private const val BASE_URL = "https://api.imgbb.com/"
    private val API_KEY = BuildConfig.IMGBB_API_KEY


    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = okhttp3.Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Client-ID $API_KEY")
            .build()
        chain.proceed(newRequest)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    val service: ImgBBApiService by lazy { retrofit.create(ImgBBApiService::class.java)}

}
