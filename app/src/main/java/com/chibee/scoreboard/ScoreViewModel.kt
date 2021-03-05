package com.chibee.scoreboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import database.Game
import database.GameDatabaseDao
import kotlinx.coroutines.*

class ScoreViewModel(val database: GameDatabaseDao, application: Application, val teamAName: String, val teamBName: String) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val _teamAScore = MutableLiveData<Int>()
    val teamAScore: LiveData<Int>
        get() = _teamAScore
    private val _teamBScore = MutableLiveData<Int>()
    val teamBScore: LiveData<Int>
        get() = _teamBScore
    private val _navigateToGameHistory = MutableLiveData<Boolean>()
    val navigateToGameHistory: LiveData<Boolean>
        get() = _navigateToGameHistory

    fun doneNavigating() {
        _navigateToGameHistory.value = false
    }

    init {
        _teamAScore.value = 0
        _teamBScore.value = 0
        Log.i("ScoreViewModel", teamAName)
    }

    fun onTeamA3Points() {
        _teamAScore.value = (_teamAScore.value)?.plus(3)
    }

    fun onTeamB3Points() {
        _teamBScore.value = (_teamBScore.value)?.plus(3)
    }

    fun onTeamA2Points() {
        _teamAScore.value = (_teamAScore.value)?.plus(2)
    }

    fun onTeamB2Points() {
        _teamBScore.value = (_teamBScore.value)?.plus(2)
    }

    fun onTeamAFreethrow() {
        _teamAScore.value = (_teamAScore.value)?.plus(1)
    }

    fun onTeamBFreethrow() {
        _teamBScore.value = (_teamBScore.value)?.plus(1)
    }

    fun onReset(){
        _teamAScore.value = 0
        _teamBScore.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)
    private var tonight = MutableLiveData<Game?>()

    fun onEndGame(){
        Log.i("teamName", teamAName)
        uiScope.launch {
            val now = System.currentTimeMillis()
            val game = Game(teamA = teamAName, teamB = teamBName, teamAScore = teamAScore.value!!, teamBScore = teamBScore.value!!, gameDate = now)
            insert(game)
            _navigateToGameHistory.value = true
        }
    }

    private suspend fun insert(game: Game){
        withContext(Dispatchers.IO){
            database.insert(game)
        }
    }

}