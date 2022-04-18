package uz.mobiler.todoapp.localdatabase

import androidx.room.*

@Dao
interface TaskerDao {

    @Insert
    fun insertTasker(tasker: Tasker)

    @Update
    fun updateTasker(tasker: Tasker)

    @Delete
    fun deleteTasker(tasker: Tasker)

    @Query("select * from tasker")
    fun getAllTasker(): List<Tasker>

}