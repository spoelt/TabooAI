package com.spoelt.taboo.ai.data.util

import android.content.Context

fun loadJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName)
        .bufferedReader()
        .use { it.readText() }
}