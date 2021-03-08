package com.chibee.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import database.Game

class GameHistoryAdapter: ListAdapter<Game, GameHistoryAdapter.GameViewHolder>(GameDiffCallback()){
    class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameDateTextView: TextView
        val teamANameTextView: TextView
        val teamBNameTextView: TextView
        val teamAScoreTextView: TextView
        val teamBScoreTextView: TextView
        init {
            gameDateTextView = view.findViewById(R.id.game_hist_game_date)
            teamANameTextView = view.findViewById(R.id.game_hist_team_A_name)
            teamBNameTextView = view.findViewById(R.id.game_hist_team_B_name)
            teamAScoreTextView = view.findViewById(R.id.game_hist_team_A_score)
            teamBScoreTextView = view.findViewById(R.id.game_hist_team_B_score)
        }
        fun bind(game: Game){
            gameDateTextView.text = game.gameDateString()
            teamANameTextView.text = game.teamA
            teamBNameTextView.text = game.teamB
            teamAScoreTextView.text = game.teamAScore.toString()
            teamBScoreTextView.text = game.teamBScore.toString()
        }

        companion object {
            fun from(parent: ViewGroup): GameViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.game_row, parent, false)
                return GameViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class GameDiffCallback : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.gameId == newItem.gameId
    }


    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }
}