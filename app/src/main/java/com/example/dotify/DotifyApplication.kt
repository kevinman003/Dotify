package com.example.dotify

import android.app.Application
import com.ericchee.songdataprovider.Song
import com.example.dotify.repository.DataRepository

class DotifyApplication : Application() {
    var selectedSong : Song? = null
    lateinit var dataRepository: DataRepository

    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
    }
}