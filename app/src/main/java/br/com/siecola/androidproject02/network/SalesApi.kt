package br.com.siecola.androidproject02.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


//obs: android acessa apenas https nativamente!
private const val BASE_URL = " https://sales-provider.appspot.com"

//aqui configuramos o Moshi
//ele é quem adapta nosso Json para Kotlin e vice versa
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//aqui configuramos o cliente http do Retrofit
//ele é a nossa biblioteca que possbilita criar e receber requisições através de interfaces
private val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(60, TimeUnit.SECONDS)
    //.addInterceptor(OauthTokenInterceptor())
    //.authenticator(OauthTokenAuthenticator())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory()) // <- para requisições assíncronas, utilizar o adaptador
    .client(okHttpClient)
    .build()

//object cria diretamente um objeto
//em outras palavras, não preciso criar uma classe e então instanciar um objeto
//para acessar seus métodos etc
object SalesApi {
}