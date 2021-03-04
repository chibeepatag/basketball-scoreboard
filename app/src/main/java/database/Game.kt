package database

data class Game (
    var gameId: Long = 0L,
    val teamA: String,
    val teamB: String,
    val teamAScore: Int,
    val teamBScore: Int

)