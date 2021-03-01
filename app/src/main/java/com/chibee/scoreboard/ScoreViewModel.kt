package com.chibee.scoreboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private val _teamAScore = MutableLiveData<Int>()
    val teamAScore: LiveData<Int>
        get() = _teamAScore

    private val _teamBScore = MutableLiveData<Int>()
    val teamBScore: LiveData<Int>
        get() = _teamBScore


    init {
        _teamAScore.value = 0
        _teamBScore.value = 0
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
    }
}