package us.ait.minesweeper

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import us.ait.minesweeper.model.MinesweeperModel
import us.ait.minesweeper.view.MinesweeperView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val animDrawable = linear_layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()

    }

    fun toggleStatus():Boolean{
        return toggle.isChecked
    }

    fun loseActivity(){
        mineView.resetView()
        startActivity(Intent(this@MainActivity, LosingActivity::class.java))
    }

    fun winActivity(){
        mineView.resetView()
        startActivity(Intent(this@MainActivity, WinActivity::class.java))
    }

    fun showText(text:String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }



}
