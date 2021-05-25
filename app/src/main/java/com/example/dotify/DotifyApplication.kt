package com.example.dotify

import android.app.Application
import com.ericchee.songdataprovider.Song
import com.example.dotify.manager.NotificationManager
import com.example.dotify.manager.SongNotifyManager
import com.example.dotify.repository.DataRepository

class DotifyApplication : Application() {
    var selectedSong : Song? = null
    lateinit var dataRepository: DataRepository
    lateinit var songNotifyManager: SongNotifyManager
    lateinit var notificationManager: NotificationManager
    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
        this.songNotifyManager = SongNotifyManager(this)
        this.notificationManager = NotificationManager(this)
    }
}