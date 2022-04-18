package uz.mobiler.todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import uz.mobiler.todoapp.adapters.ColorRvAdapters
import uz.mobiler.todoapp.databinding.FragmentAddBinding
import uz.mobiler.todoapp.localdatabase.Tasker
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    private var data: String? = null
    private var taym:String?=null

    private val TAG = "AddFragment"
    lateinit var colorRvAdapters: ColorRvAdapters
    lateinit var list: ArrayList<Tasker>
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        binding.circleIndicator.setBackgroundDrawable(resources.getDrawable(R.drawable.indicator_style_grey))

        loadData()

        colorRvAdapters= ColorRvAdapters(list)
        binding.selectRv.adapter=colorRvAdapters
        colorRvAdapters.notifyDataSetChanged()

        binding.calendar.setOnClickListener {
            val calendar = MaterialDatePicker.Builder.datePicker().build()
            calendar.addOnPositiveButtonClickListener { selection ->
                var dateFormat = SimpleDateFormat("dd.MM.yyyy")
                var date = selection?.let { it1 -> Date(it1) }
                var format = dateFormat.format(date)
                Log.d(TAG, "onPositiveButtonClick: $format")
                binding.dateTv.visibility = View.VISIBLE
                binding.dateTv.text = "$format"
            }
            calendar.show(childFragmentManager, "tag")
        }

        binding.alarm.setOnClickListener {
            var time = MaterialTimePicker.Builder()
            val build = time.build()
            time.setHour(16)
            time.setMinute(30)
            build.addOnPositiveButtonClickListener {
                Log.d(TAG, "onCreateView: ${build.hour}")
                Log.d(TAG, "onCreateView: ${build.minute}")
                binding.timeTv.visibility = View.VISIBLE
                binding.timeTv.text = "${build.hour}:${build.minute}"
            }
            build.show(childFragmentManager, "tab")
        }

        binding.cancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.done.setOnClickListener {
            val date = binding.dateTv.text.toString()
            val time = binding.timeTv.text.toString()
            if (date.isNotEmpty() && time.isNotEmpty()) {
                Snackbar.make(binding.root, "Sana va Vaqtni kiriting?", Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }


        return binding.root
    }

    private fun loadData() {
        list= ArrayList()
        list.add(Tasker("","","","Inbox",false,resources.getColor(R.color.grey)))
        list.add(Tasker("","","","Work",false,resources.getColor(R.color.green)))
        list.add(Tasker("","","","Shopping",false,resources.getColor(R.color.red)))
        list.add(Tasker("","","","Family",false,resources.getColor(R.color.yellow)))
        list.add(Tasker("","","","Personal",false,resources.getColor(R.color.purple)))

    }


}