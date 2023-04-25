package com.mymind

import android.app.Application
import com.mymind.di.Modules
import org.koin.core.context.startKoin

class GeneralApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    Modules.viewModelModules
                )
            )
        }
    }
}