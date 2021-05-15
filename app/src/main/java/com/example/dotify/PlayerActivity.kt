package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.example.dotify.databinding.PlayerActivityBinding
import kotlin.random.Random

const val SONG_KEY = "song"
const val PLAYS_KEY = "PLAYS_KEY"

fun navigateToPlayerActivity(context: Context, song: Song?) {
    val intent = Intent(context, PlayerActivity::class.java)
    val bundle = Bundle().apply {
        putParcelable(SONG_KEY, song)
    }
    Log.i("fdsa", "In navigation")
    intent.putExtras(bundle)
    context.startActivity( intent )
}

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: PlayerActivityBinding
    private lateinit var btnChangeUser: Button
    private lateinit var ibPrev: ImageButton
    private lateinit var ibPlay: ImageButton
    private lateinit var ibNext: ImageButton
    var numPlays = Random.nextInt(10000, 100000)
    private var changingUser = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlayerActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState != null) {
            numPlays = savedInstanceState.getInt(PLAYS_KEY)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.ibPrev.setOnClickListener {
            onPrev()
        }
        binding.ibPlay.setOnClickListener {
            onPlay()
        }
        binding.ibNext.setOnClickListener{
            onNext()
        }
        with(binding) {
            val song: Song? = intent.getParcelableExtra<Song>(SONG_KEY)
            tvTitle.text = song?.title
            tvArtist.text = song?.artist
            tvPlays.text = "${numPlays.toString()} plays"
            btnSettings.setOnClickListener {
                navigateToSettingsActivity(this@PlayerActivity, song, numPlays)
            }
            if (song != null) {
                ivAlbumCover.setImageResource(song?.largeImageID)
            }
        }
    }

    fun onPrev() {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun onPlay() {
        numPlays += 1
        binding.tvPlays.text = "${numPlays.toString()} plays"
    }

    fun onNext() {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PLAYS_KEY, numPlays)
        super.onSaveInstanceState(outState)
    }
}