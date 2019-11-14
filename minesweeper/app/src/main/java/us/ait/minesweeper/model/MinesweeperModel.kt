package us.ait.minesweeper.model

import kotlin.random.Random

object MinesweeperModel{
    val TILE: Int = 0
    val MINE: Int = 1
    val UNCLICKED: Boolean = false

    var bombs: Int = 0
    var flags: Int = 0



    data class Field(var type: Int = rando(), var minesAround: Int = 0, var isFlagged: Boolean = false, var wasClicked: Boolean = UNCLICKED)

    private fun rando():Int{
        val rand = Random.nextInt(0, 6)
        if (bombs <3 && rand ==1) {
            bombs++
            return MINE
            }
        else return TILE
    }

     val fieldMatrix = arrayOf(
        arrayOf(Field(), Field(), Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field(), Field(), Field())
     )

    fun winCheck():Boolean{
        return bombs == flags
    }

    fun reset(){
        bombs = 0
        flags = 0
        for (i in 0..4){
            for (j in 0..4){
                fieldMatrix[i][j] = Field()
            }
        }
    }


    private fun lowY(x: Int, y: Int):Int{
        var counter = 0
        if(y-1>=0) {
            if (fieldMatrix[x][y - 1].type == 1) counter++
            if (x - 1 >= 0)
                if (fieldMatrix[x-1][y-1].type == 1) counter++
            if (x + 1 <= 4) {
                if (fieldMatrix[x + 1][y - 1].type == 1) counter++
            }
        }
        return counter
    }

    private fun highY(x:Int, y:Int):Int{
        var counter = 0
        if(y+1<=4) {
            if (fieldMatrix[x][y + 1].type == 1) counter++
            if (x - 1 >= 0)
                if (fieldMatrix[x - 1][y + 1].type == 1) counter++
            if (x + 1 <= 4) {
                if (fieldMatrix[x + 1][y + 1].type == 1) counter++
            }
        }
        return counter
    }

    fun around(x: Int, y: Int):Int{
        var counter = highY(x, y) + lowY(x, y)

        if(x+1<=4 && fieldMatrix[x+1][y].type==1) counter++
        if(x-1>=0 && fieldMatrix[x-1][y].type==1) counter++
        return counter
    }

    fun mineCheck(x: Int, y: Int):Boolean{
        return fieldMatrix[x][y].type == 1
    }

    fun flagCheck(x: Int, y:Int): Boolean{
        return fieldMatrix[x][y].isFlagged
    }




}