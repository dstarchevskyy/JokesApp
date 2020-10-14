package com.droiddevstar.jokeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class JokeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        configureTimber()
    }

    private fun configureTimber() {
        if (BuildConfig.DEBUG) {
            val tree = Timber.DebugTree()
            Timber.plant(tree)
            Timber.tag(getString(R.string.app_name))
        }
    }

}