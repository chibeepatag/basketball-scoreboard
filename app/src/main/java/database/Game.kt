package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat

@Entity
data class Game (
    @PrimaryKey(autoGenerate = true)
    var gameId: Long = 0L,
    @ColumnInfo(name = "team_a")
    val teamA: String,
    @ColumnInfo(name = "team_b")
    val teamB: String,
    @ColumnInfo(name = "team_a_score")
    val teamAScore: Int,
    @ColumnInfo(name = "team_b_score")
    val teamBScore: Int,
    @ColumnInfo(name = "game_date")
    var gameDate: Long = System.currentTimeMillis()
){
    fun gameDateString(): String{
        val sdf = SimpleDateFormat("d MMM yyyy HH:mm")
        val formattedDate = sdf.format(gameDate).toString()
        return formattedDate
    }
}