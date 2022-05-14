package br.com.siecola.androidproject02.network

import com.squareup.moshi.Json
import retrofit2.Call

data class OauthTokenResponse(
    //neste caso utilizamos a biblioteca para interpretar o json recebido
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "expires_in")
    val expiresIn: Int,
) : Call<OauthTokenResponse>
