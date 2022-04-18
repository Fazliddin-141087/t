package uz.mobiler.todoapp.modal

import android.content.Context
import uz.mobiler.todoapp.localdatabase.AppDatabase
import uz.mobiler.todoapp.localdatabase.Tasker
import java.util.*

class Modal(context: Context,var appDatabase: AppDatabase)  : Observable() {

    init{
        appDatabase= AppDatabase.getInstance(context)
    }

    fun insert(tasker: Tasker){
        appDatabase.taskerDao().insertTasker(tasker)
        setChanged()
        notifyObservers()
    }

    fun delete(tasker: Tasker){
        appDatabase.taskerDao().deleteTasker(tasker)
        setChanged()
        notifyObservers()
    }

    fun update(tasker: Tasker){
        appDatabase.taskerDao().updateTasker(tasker)
        setChanged()
        notifyObservers()
    }

    fun all(){
        appDatabase.taskerDao().getAllTasker()
        setChanged()
        notifyObservers()
    }


}