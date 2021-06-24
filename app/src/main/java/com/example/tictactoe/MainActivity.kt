package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tictactoe.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.lastWinner.text = "Last winner: EMPTY"
    }

    fun play3(view: View) {
        val start3Intent = Intent(this, Size3::class.java)
        startActivityForResult(start3Intent, 300)
    }

    fun play5(view: View) {
        val start5Intent = Intent(this, Size5::class.java)
        startActivityForResult(start5Intent, 500)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 300 || requestCode == 500) {
            binding.lastWinner.text = "Last winner: " + data?.getStringExtra("result")
        }
    }
}