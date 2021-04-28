package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ericchee.songdataprovider.Song

fun navigateToSettingsActivity(context: Context, song: Song?, plays: Int) {
    val intent = Intent(context, SettingsActivity::class.java)
    val bundle = Bundle().apply {
        putParcelable(SONG_KEY, song)
        putInt(PLAYS_KEY, plays)
    }
    intent.putExtras(bundle)
    context.startActivity(intent)
}
class SettingsActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHost)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        navController.setGraph(R.navigation.nav_graph, Bundle().apply {
            val song: Song? = intent.getParcelableExtra<Song>(SONG_KEY)
            val plays: Int = intent.getIntExtra(PLAYS_KEY, 0)
            putParcelable(SONG_KEY, song)
            putInt("plays", plays)
        })
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}