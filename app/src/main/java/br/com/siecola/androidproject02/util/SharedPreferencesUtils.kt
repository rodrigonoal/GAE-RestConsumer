package br.com.siecola.androidproject02.util

import android.content.Context
import android.content.SharedPreferences
import br.com.siecola.androidproject02.MainApplication

//definindo variáveis que serão as chaves de nosso token
private const val ACCESS_TOKEN = "access_token"
private const val EXPIRES_IN = "expires_in"

object SharedPreferencesUtils {

    fun saveToken(accessToken: String, expiresIn: Int) {
        //estamos recebendo em segundos e precisamos transformar numa data final
        var absoluteExpiration = ((System.currentTimeMillis() /1000) + expiresIn).toInt()

        //abrindo o contexto, editando, guardando uma string com sua chave
        with(getSharedPreferences().edit()) {
            putString(ACCESS_TOKEN, accessToken)
            putInt(EXPIRES_IN, absoluteExpiration)
            commit()
        }
    }

    // quando fizermos requisições, utilizaremos este método
    fun getAccessToken(): String? {
        var accessToken: String? = null

        with (getSharedPreferences()) {
            if(contains(ACCESS_TOKEN) && contains(EXPIRES_IN)) {
                val expiresIn = getInt(EXPIRES_IN, 0)
                val currentTime = System.currentTimeMillis() / 1000

                if(currentTime < expiresIn) {
                    accessToken = getString(ACCESS_TOKEN, "")
                }
            }
        }

        return accessToken
    }


    //estabelecendo o ciclo de contexto
    private fun getSharedPreferences(): SharedPreferences {
        val context = MainApplication.getApplicationContext()
        return context.getSharedPreferences("main", Context.MODE_PRIVATE)
    }
}