package uz.digital.dagger2.di

import androidx.viewbinding.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.digital.dagger2.network.ApiService
import uz.digital.dagger2.utils.Constants
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkhttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(httpLoggingInterceptor)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideApiService(
        url: String,
        factory: GsonConverterFactory,
        client: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(factory)
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
// meat, rice, oil