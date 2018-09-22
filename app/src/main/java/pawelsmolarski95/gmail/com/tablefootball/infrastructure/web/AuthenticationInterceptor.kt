package pawelsmolarski95.gmail.com.tablefootball.infrastructure.web

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder: Request.Builder = original.newBuilder().header("Authorization", token)
        val request = builder.build()

        return chain.proceed(request)
    }
}