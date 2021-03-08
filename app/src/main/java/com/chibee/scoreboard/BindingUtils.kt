package com.chibee.scoreboard

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.chibee.scoreboard.database.Game

@BindingAdapter("gameDate")
fun TextView.setGameDate(game: Game?){
    game?.let {
        text = it.gameDateString()
    }
}

@BindingAdapter("teamAName")
fun TextView.setTeamAName(game: Game?){
    game?.let {
        text = it.teamA
    }
}

@BindingAdapter("teamBName")
fun TextView.setTeamBName(game: Game?){
    game?.let {
        text = it.teamB
    }
}

@BindingAdapter("teamAScore")
fun TextView.setTeamAScore(game: Game?){
    game?.let {
        text = it.teamAScore.toString()
    }
}

@BindingAdapter("teamBScore")
fun TextView.setTeamBScore(game: Game?){
    game?.let {
        text = it.teamBScore.toString()
    }
}