package br.eti.rafaelcouto.testworkshop

import android.app.Application
import br.eti.rafaelcouto.testworkshop.injection.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WorkshopApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WorkshopApplication)
            modules(Modules.all)

        }
    }
}