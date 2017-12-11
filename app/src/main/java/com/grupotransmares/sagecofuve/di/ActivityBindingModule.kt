package com.grupotransmares.sagecofuve.di

import com.grupotransmares.sagecofuve.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(VisitModule::class))
    internal abstract fun homeActivity(): HomeActivity
}