package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
    private var selectedSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val songs = SongDataProvider.getAllSongs()
        title = "All Songs"
        selectedSong = savedInstanceState?.getParcelable(SONG_KEY)
        with(binding) {
            // initially sets the mini player text and selected song to the first song in the player
            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter
            adapter.onSongClickListener = {song: Song ->
                tvMiniText.text = "${song.title} - ${song.artist}"
                selectedSong = song
                clMiniPlayer.visibility  = View.VISIBLE
            }
            btnShuffle.setOnClickListener {
                adapter.updateSongs(songs.toMutableList().shuffled())
            }

            val imSelectedSong = selectedSong
            if (imSelectedSong == null) {
                clMiniPlayer.visibility = View.GONE
            } else {
                tvMiniText.text = "${imSelectedSong?.title} - ${imSelectedSong?.artist}"
                clMiniPlayer.visibility = View.VISIBLE
            }
            clMiniPlayer.setOnClickListener {
                if (imSelectedSong != null) {
                    navigateToPlayerActivity(this@SongListActivity, imSelectedSong)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(SONG_KEY, selectedSong)
        super.onSaveInstanceState(outState)
    }
}