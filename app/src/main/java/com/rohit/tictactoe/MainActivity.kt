package com.rohit.tictactoe

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val winningCombo: ArrayList<ArrayList<Int>> =
                    arrayListOf(arrayListOf(0,1,2), arrayListOf(3,4,5), arrayListOf(6,7,8),
                            arrayListOf(0,3,6), arrayListOf(1,4,7), arrayListOf(2,5,8),
                            arrayListOf(0,4,8), arrayListOf(2,4,6))
    private var markedBoxes = ArrayList<Int>()
    private var xMarkedBoxes = ArrayList<Int>()
    private var oMarkedBoxes = ArrayList<Int>()
    private var isXTurn = true
    private var hasFoundWinner = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        reset_game.setOnClickListener { resetGame() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Reset the game board
     */
    private fun resetGame(){
        markedBoxes = ArrayList()
        xMarkedBoxes = ArrayList()
        oMarkedBoxes = ArrayList()
        isXTurn = true
        hasFoundWinner = false
        btn_0.text = null
        btn_1.text = null
        btn_2.text = null
        btn_3.text = null
        btn_4.text = null
        btn_5.text = null
        btn_6.text = null
        btn_7.text = null
        btn_8.text = null
        player_winner.text = null
        player_turn.text = "X's move"
    }

    /**
     * Click listener for all the boxes in the game
     */
    fun onMark(v: View){
        if(hasFoundWinner)
            return

        val btn: Button = v as Button
        val tag:Int

        try {
            tag = btn.tag.toString().toInt()
        }
        catch (nfe: NumberFormatException){
            Log.e("MainActivity::onMark", "Found number format exception")
            return
        }
        Log.d("MainActivity::onMark", "Button Tag is: " + tag)

        if(!markedBoxes.contains(tag)) {
            if (isXTurn) {
                btn.text = "X"
                player_turn.text = getString(R.string.x_move)
                xMarkedBoxes.add(tag)
            }
            else {
                btn.text = "O"
                player_turn.text = getString(R.string.o_move)
                oMarkedBoxes.add(tag)
            }

            markedBoxes.add(tag)
            isXTurn = !isXTurn
        }

        checkWinner()
    }

    /**
     * Check winner here.
     */
    private fun checkWinner(){
        for(x in winningCombo){
            if(xMarkedBoxes.containsAll(x)) {
                hasFoundWinner = true
                player_winner.text = getString(R.string.x_won)
                return
            }
            else if(oMarkedBoxes.containsAll(x)){
                hasFoundWinner = true
                player_winner.text = getString(R.string.o_won)
                return
            }
        }
    }
}
