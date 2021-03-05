package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameDatabaseDao {

    @Insert
    fun insert(game: Game)

    @Update
    fun update(game: Game)

    @Query("SELECT * from game WHERE gameId = :key")
    fun get(key: Long): Game?

    @Query("DELETE from game")
    fun clear()

    @Query("SELECT * from game ORDER BY gameId DESC")
    fun getAllGames(): LiveData<List<Game>>

    @Query("DELETE from game where gameId = :key")
    fun delete(key: Long)
}