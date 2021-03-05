package com.chibee.scoreboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import database.GameDatabaseDao
import kotlinx.coroutines.*

class GameHistoryViewModel(val database: GameDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)
    private val games = database.getAllGames()

    val gamesString = Transformations.map(games) { game ->
        game.size.toString()
    }

    fun onClear(){
        uiScope.launch {
            clear()
            _showSnackbarEvent.value = true

        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }
}