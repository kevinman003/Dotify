package com.example.dotify

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ericchee.songdataprovider.Song
import com.example.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val navController by lazy { findNavController() }
    private val safeArgs: SettingsFragmentArgs by navArgs()
    private val dotifyApp by lazy { activity?.applicationContext as DotifyApplication}

    private val songNotifyManager by lazy { dotifyApp.songNotifyManager }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)

        with(binding) {
            btnProfile.setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }
            btnAbout.setOnClickListener {
                navController.navigate(R.id.aboutFragment)
            }
            btnStats.setOnClickListener {
//                navController.navigate(R.id.statisticsFragment)
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment(song = safeArgs.song, plays = safeArgs.plays))
            }

            scNotify.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    songNotifyManager.getSong()
                } else {
                    songNotifyManager.stopGetSong()
                }
            }
        }
        return binding.root
    }
}