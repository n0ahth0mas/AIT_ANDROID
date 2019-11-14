package us.ait.minesweeper

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_losing.*

import kotlinx.android.synthetic.main.activity_win.*

class WinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        again.setOnClickListener {
            startActivity(Intent(this@WinActivity, MainActivity::class.java))
            //mineView.postInvalidate()
            finish()
        }
    }

}
