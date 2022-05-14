package br.com.siecola.androidproject02.network

data class OauthTokenResponse(
    val accessToken: String,

    val expiresIn: Int,
)
