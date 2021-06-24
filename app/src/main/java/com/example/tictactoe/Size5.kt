package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.example.tictactoe.databinding.ActivitySize5Binding

class Size5 : AppCompatActivity() {
    private lateinit var binding: ActivitySize5Binding

    enum class Symbol {
        X, O, EMPTY
    }

    private var turn = Symbol.X
    private var board = arrayOf(
            arrayOf<Symbol>(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY),
            arrayOf<Symbol>(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY),
            arrayOf<Symbol>(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY),
            arrayOf<Symbol>(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY),
            arrayOf<Symbol>(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY))

    private var boardGraphic : Array<Array<ImageButton>> = arrayOf()
    private var finished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySize5Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        boardGraphic = arrayOf(
                arrayOf(binding.row1col1, binding.row1col2, binding.row1col3, binding.row1col4, binding.row1col5),
                arrayOf(binding.row2col1, binding.row2col2, binding.row2col3, binding.row2col4, binding.row2col5),
                arrayOf(binding.row3col1, binding.row3col2, binding.row3col3, binding.row3col4, binding.row3col5),
                arrayOf(binding.row4col1, binding.row4col2, binding.row4col3,  binding.row4col4,  binding.row4col5),
                arrayOf(binding.row5col1, binding.row5col2, binding.row5col3, binding.row5col4, binding.row5col5))

        start()
    }

    private fun start() {
        finished = false
        // clear board
        for (row in 0..4) {
            for (col in 0..4) {
                board[row][col] = Symbol.EMPTY
                boardGraphic[row][col].setImageResource(0)
            }
        }
        turn = Symbol.X;
        binding.turn.text = "It's $turn turn"
    }

    fun draw(view: View) {
        if (!finished) {
            for (row in 0..4) {
                for (col in 0..4) {
                    if (view == boardGraphic[row][col] && board[row][col] == Symbol.EMPTY) {
                        board[row][col] = turn
                        if (turn == Symbol.X) {
                            boardGraphic[row][col].setImageResource(R.drawable.x)
                            turn = Symbol.O
                        } else {
                            boardGraphic[row][col].setImageResource(R.drawable.o)
                            turn = Symbol.X
                        }
                    }
                }
            }
            evaluate()
            binding.turn.text = "It's $turn turn"
        }
    }
    private fun evaluate() {
        val intent = Intent()

        for (row in 0..4) { // check in rows
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]
                && board[row][2] == board[row][3] && board[row][3] == board[row][4]) {
                if (board[row][0] == Symbol.X) {
                    binding.turn.text = "X wins!"
                    finished = true
                    sendResult(Symbol.X)
                } else if (board[row][0] == Symbol.O) {
                    binding.turn.text = "O wins!"
                    finished = true
                    sendResult(Symbol.O)
                }
            }
        }

        for (col in 0..4) { // check in columns
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]
                && board[2][col] == board[3][col] && board[3][col] == board[4][col]) {
                if (board[0][col] == Symbol.X) {
                    binding.turn.text = "X wins!"
                    finished = true
                    sendResult(Symbol.X)
                } else if (board[0][col] == Symbol.O) {
                    binding.turn.text = "O wins!"
                    finished = true
                    sendResult(Symbol.O)
                }
            }
        }

        if (board[0][4] == board[1][3] && board[1][3] == board[2][2] && board[2][2] == board[3][1]
            && board[3][1] == board[4][0]) {
            if (board[0][4] == Symbol.X) {
                binding.turn.text = "X wins!"
                finished = true
                sendResult(Symbol.X)
            } else if (board[0][4] == Symbol.O) {
                binding.turn.text = "O wins!"
                finished = true
                sendResult(Symbol.O)
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == board[3][3]
            && board[3][3] == board[4][4]) {
            if (board[0][0] == Symbol.X) {
                binding.turn.text = "X wins!"
                finished = true
                sendResult(Symbol.X)
            } else if (board[0][0] == Symbol.O) {
                binding.turn.text = "O wins!"
                finished = true
                sendResult(Symbol.O)
            }
        }

        if (!isMoveLeft()) {
            binding.turn.text = "Tie!"
            finished = true
            sendResult(Symbol.EMPTY)
        }
    }

    private fun sendResult(winner: Symbol) {
        val intent = Intent()
        intent.putExtra("result", winner.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
    private fun isMoveLeft(): Boolean {
        for (row in 0..4) {
            for (col in 0..4) {
                if (board[row][col] == Symbol.EMPTY) {
                    return true;
                }
            }
        }
        return false
    }
}