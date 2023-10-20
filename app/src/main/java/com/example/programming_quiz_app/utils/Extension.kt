package com.example.programming_quiz_app.utils

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun AssetManager.readFile(fileName: String) = open(fileName)
    .bufferedReader()
    .use { it.readText()

    }

fun <T : Any> ViewModel.ioThenMain(
    work: suspend () -> T?,
    callback: (T?) -> Unit
) {
    viewModelScope.launch(Dispatchers.Main) {
        val data = withContext(Dispatchers.IO) {
            return@withContext work()
        }
        callback(data)
    }
}