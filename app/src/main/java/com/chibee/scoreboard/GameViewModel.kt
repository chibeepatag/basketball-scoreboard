package com.chibee.scoreboard

import android.app.Application
import androidx.lifecycle.*
import com.chibee.scoreboard.database.Game
import com.chibee.scoreboard.database.GameDatabaseDao
import kotlinx.coroutines.*

class GameViewModel(
    private val gameId: Long,
    val database: GameDatabaseDao) : ViewModel(){


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)
    private val game = MediatorLiveData<Game>()

    fun getGame() = game

    init{
       game.addSource(database.getGameWithId(gameId), game::setValue)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}

