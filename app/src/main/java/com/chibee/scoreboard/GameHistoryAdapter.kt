package com.chibee.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chibee.scoreboard.database.Game
import com.chibee.scoreboard.databinding.GameRowBinding

class GameHistoryAdapter: ListAdapter<Game, GameHistoryAdapter.GameViewHolder>(GameDiffCallback()){
    class GameViewHolder private constructor(val binding: GameRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Game){
            binding.game = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GameViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GameRowBinding.inflate(layoutInflater, parent, false)
                return GameViewHolder(binding)
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