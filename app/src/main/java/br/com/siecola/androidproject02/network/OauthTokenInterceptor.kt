package br.com.siecola.androidproject02.network

import android.util.Log
import br.com.siecola.androidproject02.util.SharedPreferencesUtils
import okhttp3.Interceptor
import okhttp3.Response

private const val TAG = "OauthTokenInterceptor"

class OauthTokenInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        Log.i(TAG, "Fetching access token from shared preferences")
        val accessToken = SharedPreferencesUtils.getAccessToken()

        if(accessToken != null) {
            Log.i(TAG, "Using the ecxisting token")
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer ${accessToken}")
                .build()
        }

        return chain.proceed(request)
    }
}