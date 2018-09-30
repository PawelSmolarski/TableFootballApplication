package pawelsmolarski95.gmail.com.tablefootball.infrastructure.web

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import kotlin.reflect.KClass

class TestServiceBuilder {
    companion object {
        fun <T : Any> create(service: KClass<out T>): BehaviorDelegate<out T> {

            val retrofit = Retrofit.Builder().baseUrl("http://test.com")
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()


            val behavior = NetworkBehavior.create()

            val mockRetrofit = MockRetrofit.Builder(retrofit)
                    .networkBehavior(behavior)
                    .build()

            return mockRetrofit.create(service.java)
        }
    }
}