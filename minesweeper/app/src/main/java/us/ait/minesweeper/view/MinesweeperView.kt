package us.ait.minesweeper.view

import android.content.Context
import android.graphics.*
import android.media.MediaPlayer
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.view.*
import us.ait.minesweeper.MainActivity
import us.ait.minesweeper.R
import us.ait.minesweeper.model.MinesweeperModel

class MinesweeperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    var paintBackground: Paint = Paint()
    var paintLine: Paint = Paint()
    var paintText: Paint = Paint()
    var paintMine: Paint = Paint()
    var paintClick: Paint = Paint()
    var paintFlag: Paint = Paint()

    var paint0: Paint = Paint()
    var paint1: Paint = Paint()
    var paint2: Paint = Paint()
    var paint3: Paint = Paint()
    var paint4: Paint = Paint()
    var paint5: Paint = Paint()
    var paint6: Paint = Paint()

    private var bitmapBomb: Bitmap = BitmapFactory.decodeResource(
        context?.resources, R.drawable.bomb
    )

    private var bitmapFlag: Bitmap = BitmapFactory.decodeResource(
        context?.resources, R.drawable.flag
    )

    private var bitmapTile: Bitmap = BitmapFactory.decodeResource(
        context?.resources, R.drawable.purpletile
    )

    private var bitmapFlip: Bitmap = BitmapFactory.decodeResource(
        context?.resources, R.drawable.flip2
    )
    private var running = true


    init {
        paintBackground.color = Color.parseColor("#850557")
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.parseColor("#1f071c")
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 20f

        paintMine.color = Color.RED
        paintMine.style = Paint.Style.STROKE
        paintMine.strokeWidth = 20f

        paint1.color = Color.parseColor("#6369D1")
        paint2.color = Color.parseColor("#EFCB68")
        paint3.color = Color.parseColor("#C96480")
        paint4.color = Color.parseColor("#A54657")
        paint5.color = Color.parseColor("#89043D")
        paint6.color = Color.parseColor("#8D3B72")

        paintClick.color = Color.GREEN
        paintClick.style = Paint.Style.STROKE
        paintClick.strokeWidth = 20f

        paintFlag.color = Color.BLUE
        paintFlag.style = Paint.Style.STROKE
        paintFlag.strokeWidth = 20f



    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paintText.textSize = height / 5f
        paint0.textSize = height / 5f
        paint1.textSize = height / 5f
        paint2.textSize = height / 5f
        paint3.textSize = height / 5f
        paint4.textSize = height / 5f
        paint5.textSize = height / 5f
        paint6.textSize = height / 5f

        bitmapBomb = Bitmap.createScaledBitmap(bitmapBomb, width / 5, height / 5, false)

        bitmapFlag = Bitmap.createScaledBitmap(bitmapFlag, width / 5, height / 5, false)

        bitmapTile = Bitmap.createScaledBitmap(bitmapTile, width / 5, height / 5, false)

        bitmapFlip = Bitmap.createScaledBitmap(bitmapFlip, width / 5, height / 5, false)

        if(MinesweeperModel.bombs == 1) (context as MainActivity).showText("There is 1 bomb on the grid")
        else (context as MainActivity).showText("There are ${MinesweeperModel.bombs} bombs on the grid")

        for (i in 0..4) {
            for (j in 0..4) {
                MinesweeperModel.fieldMatrix[i][j].minesAround = MinesweeperModel.around(i, j)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackground)

        drawBoard(canvas)
        drawMines(canvas)

    }

    private fun drawBoard(canvas: Canvas?) {
        for(i in 0..4){
            for(j in 0..4){
                tileHelper(canvas, i, j)
            }
        }
    }

    private fun drawMines(canvas: Canvas?) {
        for (i in 0..4) {
            for (j in 0..4) {
                if (MinesweeperModel.fieldMatrix[i][j].wasClicked) {
                    if (MinesweeperModel.flagCheck(i,j) && MinesweeperModel.mineCheck(i,j)) {
                        flagHelper(canvas, i, j)
                    } else if (MinesweeperModel.mineCheck(i, j)) {
                        mineHelper(canvas, i, j)
                    }  else if (MinesweeperModel.fieldMatrix[i][j].wasClicked && running) {
                        val X = (i * width / 5 + width / 20).toFloat()
                        val Y = (j * height / 5 + height / 6).toFloat()
                        tileHelper(canvas,i,j)
                        if(MinesweeperModel.fieldMatrix[i][j].minesAround != 0) {
                            canvas?.drawText(
                                "${MinesweeperModel.fieldMatrix[i][j].minesAround}",
                                X,
                                Y,
                                numColor(MinesweeperModel.fieldMatrix[i][j].minesAround)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun mineHelper(canvas: Canvas?, i:Int, j: Int) {
        canvas?.drawBitmap(
            bitmapBomb,
            (i * height / 5).toFloat(),
            (j * width / 5).toFloat(),
            null
        )
    }

    private fun flagHelper(canvas: Canvas?, i:Int, j: Int) {
        canvas?.drawBitmap(
            bitmapFlag,
            (i * height / 5).toFloat(),
            (j * width / 5).toFloat(),
            null
        )
    }

    private fun tileHelper(canvas: Canvas?, i:Int, j: Int) {
        if( MinesweeperModel.fieldMatrix[i][j].wasClicked){
            canvas?.drawBitmap(bitmapFlip, (i * height / 5).toFloat(), (j * width / 5).toFloat(), null)
        } else {
            canvas?.drawBitmap(bitmapTile, (i * height / 5).toFloat(), (j * width / 5).toFloat(), null)
        }
    }



    private fun numColor(num: Int): Paint {
        return when (num) {
            0 -> paint0
            1 -> paint1
            2 -> paint2
            3 -> paint3
            4 -> paint4
            5 -> paint5
            else -> paint6
        }
    }

    fun resetView(){
        MinesweeperModel.reset()
        invalidate()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(MinesweeperModel.winCheck()){
            (context as MainActivity).winActivity()
        }
        else {
            if (event?.action == MotionEvent.ACTION_DOWN) {
                val tX = event.x.toInt() / (width / 5)
                val tY = event.y.toInt() / (height / 5)
                if (tX < 5 && tY < 5 && MinesweeperModel.fieldMatrix[tX][tY].wasClicked == MinesweeperModel.UNCLICKED) {
                    if ((context as MainActivity).toggleStatus()) {
                        if (MinesweeperModel.fieldMatrix[tX][tY].type == MinesweeperModel.TILE) {
                            (context as MainActivity).loseActivity()
                        } else {
                            flagFlip(tX,tY)
                        }
                    } else {
                        if (MinesweeperModel.fieldMatrix[tX][tY].type == MinesweeperModel.MINE) {
                            (context as MainActivity).loseActivity()
                        } else {
                            MinesweeperModel.fieldMatrix[tX][tY].wasClicked = true
                        }
                    }
                }
            }
        }
        invalidate()
        return true
    }

    private fun flagFlip(tX : Int, tY: Int){
        MinesweeperModel.fieldMatrix[tX][tY].wasClicked = true
        MinesweeperModel.fieldMatrix[tX][tY].isFlagged = true
        MinesweeperModel.flags += 1
    }
}

