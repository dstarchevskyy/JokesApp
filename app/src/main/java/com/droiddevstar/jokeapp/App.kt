package com.droiddevstar.jokeapp


import android.app.Application
import com.droiddevstar.jokeapp.di.AppComponent
import com.droiddevstar.jokeapp.di.DaggerAppComponent

//import com.droiddevstar.jokeapp.di.AppComponent

class App : Application() {

    companion object {
        private var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)
    }
}