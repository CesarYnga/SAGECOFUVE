package com.grupotransmares.sagecofuve.di

import com.grupotransmares.sagecofuve.home.agenda.AgendaFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class VisitModule {

    @ContributesAndroidInjector
    internal abstract fun tasksFragment(): AgendaFragment
}