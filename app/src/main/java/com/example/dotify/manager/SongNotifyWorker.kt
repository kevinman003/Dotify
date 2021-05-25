package com.example.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.*
import kotlin.random.Random

class SongNotifyWorker(
    private val context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {
    private val dotifyApp by lazy { context.applicationContext as DotifyApplication }
    private val notificationManager by lazy { dotifyApp.notificationManager }

    override suspend fun doWork(): Result {
        val song = SongDataProvider.getAllSongs().random()
        notificationManager.publishSongNotification(song)
        return Result.success()
    }


}