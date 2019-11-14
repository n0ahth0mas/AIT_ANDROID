package us.ait.shoppinglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var threadEnabled = false

    inner class threadWait : Thread(){
        override fun run() {
            while (threadEnabled){
                sleep(3000)
                runSecondActivity()
                threadEnabled=false
            }
        }
    }

    fun runSecondActivity(){
        var intent = Intent()
        intent.setClass(this@MainActivity, ShoppingActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        threadEnabled = true
        threadWait().start()
    }
}
