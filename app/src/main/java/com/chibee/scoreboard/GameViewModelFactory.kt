package com.chibee.scoreboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chibee.scoreboard.database.GameDatabaseDao

class GameViewModelFactory(private val dataSource: GameDatabaseDao, private val id: Long) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(id, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}