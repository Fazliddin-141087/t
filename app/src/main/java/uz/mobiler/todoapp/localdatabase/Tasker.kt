package uz.mobiler.todoapp.localdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Tasker {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var taskName: String? = null
    var taskDate: String? = null
    var taskTime: String? = null
    var taskCategory: String? = null
    var taskActive: Boolean? = null
    var taskColor: Int? = null

    constructor()

    constructor(
        taskName: String?,
        taskDate: String?,
        taskTime: String?,
        taskCategory: String?,
        taskActive: Boolean?,
        taskColor: Int?
    ) {
        this.taskName = taskName
        this.taskDate = taskDate
        this.taskTime = taskTime
        this.taskCategory = taskCategory
        this.taskActive = taskActive
        this.taskColor = taskColor
    }


}