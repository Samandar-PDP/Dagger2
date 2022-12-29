package uz.digital.dagger2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.digital.dagger2.model.actual.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM User WHERE userId = :id")
    suspend fun deleteUser(id: Long)

    @Query("SELECT * FROM User ORDER BY userId DESC")
    fun getAllUsers(): Flow<List<User>>
}