package com.chibee.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import database.Game

class GameHistoryAdapter: RecyclerView.Adapter<GameHistoryAdapter.GameViewHolder>(){
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
    var data = listOf<Game>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

}