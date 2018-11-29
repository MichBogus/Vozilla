package application.maindi.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    private val CONNECTION_TIMEOUT_IN_SECONDS = 30L
    private val SOCKET_READ_TIMEOUT_IN_SECONDS = 30L

    @Provides
    fun provideOkHttpClient() =
            OkHttpClient.Builder()
                    .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .readTimeout(SOCKET_READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .build()
}