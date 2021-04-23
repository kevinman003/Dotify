package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.databinding.ActivitySongListBinding

fun navigateToSongListActivity(context: Context) {
    val intent = Intent(context, SongListActivity::class.java)
    context.startActivity(intent)
}

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding
    private lateinit var selectedSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val songs = SongDataProvider.getAllSongs()

        with(binding) {
            // initially sets the mini player text and selected song to the first song in the player
            selectedSong = songs[0]
            tvMiniText.text = "${songs[0].title} - ${songs[0].artist}"
            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter
            adapter.onSongClickListener = {song: Song ->
                tvMiniText.text = "${song.title} - ${song.artist}"
                selectedSong = song
            }
            btnShuffle.setOnClickListener {
                adapter.updateSongs(songs.toMutableList().shuffled())
            }
            clMiniPlayer.setOnClickListener {
                navigateToPlayerActivity(this@SongListActivity, selectedSong)
            }
        }
    }
}