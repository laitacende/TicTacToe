package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.example.tictactoe.databinding.ActivitySize3Binding


class Size3 : AppCompatActivity() {

    private lateinit var binding: ActivitySize3Binding

    enum class Symbol {
        X, O, EMPTY
    }

    private var turn = Symbol.X
    private var board = arrayOf(
            arrayOf<Symbol>(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY),
            arrayOf<Symbol>(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY),
            arrayOf<Symbol>(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY))

    private var boardGraphic : Array<Array<ImageButton>> = arrayOf()
    private var finished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySize3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        boardGraphic = arrayOf(
            arrayOf(binding.row1col1, binding.row1col2, binding.row1col3),
            arrayOf(binding.row2col1, binding.row2col2, binding.row2col3),
            arrayOf(binding.row3col1, binding.row3col2, binding.row3col3))

         start()
    }

    private fun start() {
        finished = false
        // clear board
        for (row in 0..2) {
            for (col in 0..2) {
                board[row][col] = Symbol.EMPTY
                boardGraphic[row][col].setImageResource(0)
            }
        }
        turn = Symbol.X;
        binding.turn.text = "It's $turn turn"
    }

    fun draw(view: View) {
        if (!finished) {
            for (row in 0..2) {
                for (col in 0..2) {
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

        for (row in 0..2) { // check in rows
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
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

        for (col in 0..2) { // check in columns
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
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

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == Symbol.X) {
                binding.turn.text = "X wins!"
                finished = true
                sendResult(Symbol.X)
            } else if (board[0][2] == Symbol.O) {
                binding.turn.text = "O wins!"
                finished = true
                sendResult(Symbol.O)
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
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
        for (row in 0..2) {
            for (col in 0..2) {
                if (board[row][col] == Symbol.EMPTY) {
                    return true;
                }
            }
        }
        return false
    }
}