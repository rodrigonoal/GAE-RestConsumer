package br.com.siecola.androidproject02.network

import android.util.Log
import br.com.siecola.androidproject02.util.SharedPreferenceUtils
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Call

private const val TAG = "OauthTokenAuthenticator"

class OauthTokenAuthenticator(): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = retrieveNewToken()
        SharedPreferenceUtils.saveToken(token.accessToken, token.expiresIn)

        return response
            .request()
            .newBuilder()
            .header("Authorization", "Beares ${token.accessToken}")
            .build()
    }

    private fun retrieveNewToken(): Call<OauthTokenResponse> {
        Log.i(TAG, "Retrieving new token")

        //exemplo hardcoded de como seria uma requisição de token:
        return  SalesApi.retrofitService.getToken(
            "Basic c2llY29sYTptYXRpbGRl",
            "password",
            "rodrigo@noal.com",
            "noal"
        ).execute().body()!!
        //este é apenas um exemplo didático
        //originalmente deveríamos tratar os possíveis erros etc
    }
}