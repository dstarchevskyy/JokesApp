package com.droiddevstar.jokeapp.di

import com.droiddevstar.jokeapp.App
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface AppComponent {

    companion object {
        fun create(): AppComponent {
            return DaggerAppComponent.create()
        }
    }

    fun inject(app: App)

    fun provideRetrofit(): Retrofit
}