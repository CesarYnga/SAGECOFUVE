package com.grupotransmares.sagecofuve.di

import com.grupotransmares.sagecofuve.home.chatbot.ChatbotFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatbotModule {

    @ContributesAndroidInjector
    internal abstract fun chatbotFragment(): ChatbotFragment
}