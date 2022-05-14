package br.com.siecola.androidproject02.util

import android.content.Context
import android.content.SharedPreferences
import br.com.siecola.androidproject02.MainApplication

//definindo variáveis que serão as chaves de nosso token
private const val ACCESS_TOKEN = "access_token"
private const val EXPIRES_IN = "expires_in"

class SharedPreferenceUtils {



    //estabelecendo o ciclo de contexto
    private fun getSharedPreferences(): SharedPreferences {
        val context = MainApplication.getApplicationContext()
        return context.getSharedPreferences("main", Context.MODE_PRIVATE)
    }
}