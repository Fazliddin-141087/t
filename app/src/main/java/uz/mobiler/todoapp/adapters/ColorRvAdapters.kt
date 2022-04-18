package uz.mobiler.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.mobiler.todoapp.databinding.ItemColorBinding
import uz.mobiler.todoapp.localdatabase.Tasker

class ColorRvAdapters(var list: ArrayList<Tasker>) : RecyclerView.Adapter<ColorRvAdapters.Vh>() {

    private var myOnItemClickListener: MyOnItemClickListener? = null
    fun setOnItem(myOnItemClickListener: MyOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener
    }

   // private var list = ArrayList<Tasker>()

//    fun setData(list: ArrayList<Tasker>) {
//        this.list = list
//        notifyDataSetChanged()
//    }

    inner class Vh(var itemColorBinding: ItemColorBinding) :
        RecyclerView.ViewHolder(itemColorBinding.root) {
        fun onBind(tasker: Tasker) {
            itemColorBinding.taskName.text = tasker.taskCategory

            //val array = arrayOf(R.color.grey, R.color.green, R.color.red, R.color.yellow, R.color.purple)

            itemColorBinding.liner.setBackgroundColor(tasker.taskColor!!)

            itemView.setOnClickListener {
                myOnItemClickListener?.onItemClick(tasker)
            }

            itemView.setOnClickListener {
                myOnItemClickListener?.onItemSelect(tasker,itemColorBinding.selectImg)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface MyOnItemClickListener {
        fun onItemSelect(tasker: Tasker,imageView: ImageView)
        fun onItemClick(tasker: Tasker)
    }

}