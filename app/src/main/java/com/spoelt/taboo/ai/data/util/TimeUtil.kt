package com.spoelt.taboo.ai.data.util

import kotlin.time.Duration.Companion.milliseconds

fun formatTimeLeft(timeMillis: Long): String {
    val duration = timeMillis.milliseconds
    val minutes = duration.inWholeMinutes
    val seconds = (duration.inWholeSeconds % 60)
    return "%02d:%02d".format(minutes, seconds)
}