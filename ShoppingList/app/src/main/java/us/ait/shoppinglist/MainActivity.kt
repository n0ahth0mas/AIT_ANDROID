package us.ait.shoppinglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    fun runSecondActivity(){
        var intent = Intent()
        intent.setClass(this@MainActivity, ShoppingActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var iconAnim: Animation = AnimationUtils.loadAnimation(
            this@MainActivity, R.anim.shop_icon)

        var logoAnim: Animation = AnimationUtils.loadAnimation(
            this@MainActivity, R.anim.text_anim)

        iconAnim.setAnimationListener(
            object: Animation.AnimationListener{
                override fun onAnimationRepeat(animation: Animation?) {
                }
                override fun onAnimationEnd(animation: Animation?) {
                    runSecondActivity()
                }

                override fun onAnimationStart(animation: Animation?) {
                }
            }
        )
        circle.startAnimation(logoAnim)
        bigtext.startAnimation(iconAnim)

    }
}
