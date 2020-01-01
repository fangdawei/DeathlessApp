package club.fdawei.deathlessapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvExceptionInMain.setOnClickListener {
            it.post {
                throw RuntimeException("Test RuntimeException Occurs")
            }
        }

        tvExceptionInChild.setOnClickListener {
            it.post {
                Thread(Runnable {
                    throw RuntimeException("Test RuntimeException Occurs")
                }).start()
            }
        }

        tvInfo.postDelayed(object : Runnable {
            override fun run() {
                tvInfo.text = "${currCount++}"
                tvInfo.postDelayed(this, 1000)
            }
        }, 1000)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
