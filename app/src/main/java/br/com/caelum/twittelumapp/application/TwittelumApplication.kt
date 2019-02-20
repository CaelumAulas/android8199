package br.com.caelum.twittelumapp.application

import android.app.Application

class TwittelumApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instancia = this
    }

    companion object {
        private lateinit var instancia: TwittelumApplication
        fun getInstancia(): TwittelumApplication = instancia
    }

}