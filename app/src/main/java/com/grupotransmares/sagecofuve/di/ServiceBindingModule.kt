package com.grupotransmares.sagecofuve.di

import com.grupotransmares.sagecofuve.tracking.TrackingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(AgendaModule::class))
    internal abstract fun trackingService(): TrackingService
}