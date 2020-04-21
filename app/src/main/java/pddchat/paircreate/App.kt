package pddchat.paircreate

import android.app.Application
import android.content.Context
import pddchat.paircreate.log.AppTree
import timber.log.Timber

class App : Application() {

    companion object {
        // TODO どこからでも呼べるように一旦定義。DaggerでDIするように修正する
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        setupLogger()

        context = this
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(AppTree())
        }
    }
}