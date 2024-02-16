package com.radea.alquranku

import android.app.Application
import com.radea.alquranku.core.data.di.databaseModule
import com.radea.alquranku.core.data.di.networkModule
import com.radea.alquranku.core.data.di.repositoryModule
import com.radea.alquranku.di.useCaseModule
import com.radea.alquranku.di.viewModelModule
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
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}