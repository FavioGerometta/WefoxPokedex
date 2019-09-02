package ar.com.favio.wefoxpokedex.core.dagger.modules

import ar.com.favio.wefoxpokedex.core.data.network.ApiService
import ar.com.favio.wefoxpokedex.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val REQUEST_TIMEOUT = 10
    private var okHttpClient: OkHttpClient? = null

    @Singleton
    @Provides
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    internal fun providesClient(): OkHttpClient {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader(
                    "Connection",
                    "close"
                ).build()
                chain.proceed(request)
            }
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()

        return (okHttpClient)!!
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(providesClient())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}