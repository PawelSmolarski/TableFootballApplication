package pawelsmolarski95.gmail.com.tablefootball.infrastructure.web

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

class TableFootballServiceBuilder {
    companion object {
        private const val baseUrl: String = "http://192.168.1.21:8080/"
        private val baseOkHttpClient = OkHttpClient()
        private var token: String = ""

        private fun createOkHttpClient(): OkHttpClient {
            val okHttpBuilder = baseOkHttpClient
                    .newBuilder()
                    .followRedirects(false)
                    .connectTimeout(6, TimeUnit.SECONDS)
                    .readTimeout(6, TimeUnit.SECONDS)
                    .writeTimeout(6, TimeUnit.SECONDS)

            val authenticationInterceptor: AuthenticationInterceptor

            if (token.isNotEmpty()) {
                val token:String = this.token
                authenticationInterceptor = AuthenticationInterceptor(token)

                if (!baseOkHttpClient.interceptors().contains(authenticationInterceptor)) {
                    okHttpBuilder.addInterceptor(authenticationInterceptor)
                }
            }
            return okHttpBuilder.build()
        }

        fun <T : Any> create(service: KClass<out T>): T {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(createOkHttpClient())
                    .baseUrl(baseUrl)
                    .build()

            return retrofit.create(service.java)
        }
    }
}