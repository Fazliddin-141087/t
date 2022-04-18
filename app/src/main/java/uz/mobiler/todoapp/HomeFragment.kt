package uz.mobiler.todoapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mobiler.todoapp.databinding.FragmentHomeBinding
import uz.mobiler.todoapp.localdatabase.AppDatabase
import uz.mobiler.todoapp.modal.Modal

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var modal: Modal? = null
    lateinit var appDatabase: AppDatabase
    var isClicked = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(binding.root.context)

        modal = Modal(binding.root.context, appDatabase)

        binding.fabButton.setOnClickListener {
            var popupMenu = PopupMenu(binding.root.context, binding.fabButton)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.task -> {
                        findNavController().navigate(R.id.addFragment)
                    }
                    R.id.list -> {
                        Toast.makeText(binding.root.context, "List Bosildi", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }

            if (!isClicked) {
                isClicked = true
                binding.fabButton.setImageResource(R.drawable.ic_clear)
            } else if (isClicked) {
                isClicked = false
                binding.fabButton.setImageResource(R.drawable.ic_combined_shape)
            }

            popupMenu.show()
        }


        return binding.root
    }

}