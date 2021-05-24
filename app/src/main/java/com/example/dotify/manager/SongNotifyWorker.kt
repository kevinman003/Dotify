package com.example.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import com.example.dotify.R
import androidx.work.WorkerParameters
import com.ericchee.songdataprovider.SongDataProvider

class SongNotifyWorker(
    private val context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {
    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        initNotificationChannel()
    }

    override suspend fun doWork(): Result {
        val song = SongDataProvider.getAllSongs().random()
        Log.i("fdsa", song.title)
        var builder = NotificationCompat.Builder(context, SONG_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("${song.artist} just released a new song!")
            .setContentText("Listen to ${song.title} now on Dotify")
            .setStyle(
                NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        return Result.success()
    }

    private fun initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(SONG_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }
}