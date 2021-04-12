package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.dotify.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var btnChangeUser: Button
    private lateinit var ibPrev: ImageButton
    private lateinit var ibPlay: ImageButton
    private lateinit var ibNext: ImageButton
    var randomNum = Random.nextInt(10000, 100000)
    private var changingUser = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.tvTitle.text = "All Star"
        binding.tvArtist.text = "Smash Mouth"
        binding.tvPlays.text = "${randomNum.toString()} plays"
        binding.tvUser.text = "Shrek Lover"
        binding.etChangeUser.visibility = View.GONE

        binding.btnChangeUser.setOnClickListener {
            changeUser()
        }

        binding.ibPrev.setOnClickListener {
            onPrev()
        }
        binding.ibPlay.setOnClickListener {
            onPlay()
        }
        binding.ibNext.setOnClickListener{
            onNext()
        }
    }

    fun changeUser() {
        if (changingUser) {
            val user = binding.etChangeUser.text.toString()
            if (!user.isNullOrBlank()) {
                changingUser = false
                binding.etChangeUser.visibility = View.GONE
                binding.tvUser.visibility = View.VISIBLE
                binding.tvUser.text = user
                binding.btnChangeUser.text = "Change User"
            } else {
                Toast.makeText(this, "User cannot be empty", Toast.LENGTH_SHORT).show()
            }

        } else {
            changingUser = true
            binding.tvUser.visibility = View.GONE
            binding.etChangeUser.visibility = View.VISIBLE
            binding.btnChangeUser.text = "Apply"
        }
    }

    fun onPrev() {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun onPlay() {
        randomNum += 1
        binding.tvPlays.text = "${randomNum.toString()} plays"
    }

    fun onNext() {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }
}