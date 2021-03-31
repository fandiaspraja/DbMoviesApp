package com.fandiaspraja.dbmovieapp

import android.app.Application
import com.fandiaspraja.dbmovieapp.core.di.databaseModule
import com.fandiaspraja.dbmovieapp.core.di.networkModule
import com.fandiaspraja.dbmovieapp.core.di.repositoryModule
import com.fandiaspraja.dbmovieapp.di.useCaseModule
import com.fandiaspraja.dbmovieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}