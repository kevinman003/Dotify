package com.example.dotify.manager

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.res.ResourcesCompat
import androidx.work.*
import androidx.work.WorkManager
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.DotifyApplication
import java.util.concurrent.TimeUnit

const val GET_SONG_TAG = "GET_SONG_TAG"
const val SONG_CHANNEL_ID = "SONG_CHANNEL_ID"

class SongNotifyManager(context: Context) {
    private val workManager: WorkManager = WorkManager.getInstance(context)

    fun getSong() {
        if (isGetSongRunning()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<SongNotifyWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(GET_SONG_TAG)
            .build()
        workManager.enqueue(request)
    }

    fun stopGetSong() {
        workManager.cancelAllWorkByTag(GET_SONG_TAG)
    }

    private fun isGetSongRunning(): Boolean {
        return workManager.getWorkInfosByTag(GET_SONG_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }
}

