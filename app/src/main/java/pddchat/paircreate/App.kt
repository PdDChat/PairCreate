package pddchat.paircreate

import android.app.Application
import pddchat.paircreate.log.AppTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupLogger()
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(AppTree())
        }
    }
}