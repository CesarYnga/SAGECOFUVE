package com.grupotransmares.sagecofuve.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Binds


@Module
abstract class ApplicationModule {

    @Binds
    internal abstract fun bindContext(application: Application): Context
}