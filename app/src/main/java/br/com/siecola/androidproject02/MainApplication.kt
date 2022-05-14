package br.com.siecola.androidproject02

import android.app.Application

class MainApplication: Application() {

    init {
        instance = this
    }

    //implementação de singleton
    companion object {
        //aqui temos a chamada da instância, que pode ser nula
        private var instance: MainApplication? = null
    }

    // é necessário que a aplicação seja um singleton!
}