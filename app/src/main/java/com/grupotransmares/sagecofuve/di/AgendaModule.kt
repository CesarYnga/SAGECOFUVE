package com.grupotransmares.sagecofuve.di

import com.grupotransmares.sagecofuve.home.agenda.AgendaFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AgendaModule {

    @ContributesAndroidInjector
    internal abstract fun agendaFragment(): AgendaFragment
}