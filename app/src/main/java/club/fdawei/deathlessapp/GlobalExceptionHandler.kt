package club.fdawei.deathlessapp

import android.os.Looper
import android.util.Log

/**
 * Created by david on 2020/01/01.
 */
object GlobalExceptionHandler : Thread.UncaughtExceptionHandler {

    private const val TAG = "Deathless"
    private var defaultUncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null

    fun init() {
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.e(TAG, "uncaughtException in ${t.name}, ${e.message}")
        if (t == Looper.getMainLooper().thread) {
            if (needCatch(t, e)) {
                continueLooper()
            } else {
                defaultUncaughtExceptionHandler?.uncaughtException(t, e)
            }
        } else {
            if (needCatch(t, e)) {
                return
            } else {
                defaultUncaughtExceptionHandler?.uncaughtException(t, e)
            }
        }
    }

    private fun continueLooper() {
        try {
            Looper.loop()
        } catch (e: Exception) {
            val thread = Thread.currentThread()
            uncaughtException(thread, e)
        }
    }

    private fun needCatch(t: Thread, e: Throwable): Boolean {
        return true
    }
}