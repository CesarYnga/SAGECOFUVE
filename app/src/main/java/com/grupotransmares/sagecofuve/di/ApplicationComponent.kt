package com.grupotransmares.sagecofuve.di

import android.app.Application
import com.grupotransmares.sagecofuve.MyApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import dagger.BindsInstance



@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class
))
interface ApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }
}