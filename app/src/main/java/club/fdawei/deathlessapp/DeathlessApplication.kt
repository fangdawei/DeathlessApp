package club.fdawei.deathlessapp

import android.app.Application

/**
 * Created by david on 2020/01/01.
 */
class DeathlessApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.init()
    }
}