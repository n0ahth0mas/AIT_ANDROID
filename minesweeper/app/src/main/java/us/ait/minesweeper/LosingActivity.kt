package us.ait.minesweeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_losing.*
import kotlinx.android.synthetic.main.activity_main.*
import us.ait.minesweeper.model.MinesweeperModel
import us.ait.minesweeper.view.MinesweeperView

class LosingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_losing)

        again.setOnClickListener {
            startActivity(Intent(this@LosingActivity, MainActivity::class.java))
            //mineView.postInvalidate()
            finish()
        }

    }
}
