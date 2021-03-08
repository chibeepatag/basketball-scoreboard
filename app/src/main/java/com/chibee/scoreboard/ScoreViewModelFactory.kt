package com.chibee.scoreboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chibee.scoreboard.database.GameDatabaseDao

class ScoreViewModelFactory(private val dataSource: GameDatabaseDao, private val application: Application, private val teamA: String, private val teamB: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(dataSource, application, teamA, teamB) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}