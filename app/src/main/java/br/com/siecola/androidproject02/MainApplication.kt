package br.com.siecola.androidproject02

import android.app.Application
import android.content.Context

class MainApplication: Application() {

    init {
        instance = this
    }

    //implementação de singleton
    companion object {
        //aqui temos a chamada da instância, que pode ser nula
        private var instance: MainApplication? = null

        //aqui fazemos a aplicação utilizar o contexto
        //aqui é onde guardamos informações que poderemos obter em toda a aplicação
        //lembrete: é necessário incluir no manifest o "android name"
        fun getApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    // é necessário que a aplicação seja um singleton!
}