package com.gmail.konstantin.bezzemelnyi.todolist.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gmail.konstantin.bezzemelnyi.todolist.R
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity
import com.gmail.konstantin.bezzemelnyi.todolist.data.viewmodel.ToDoViewModel
import com.gmail.konstantin.bezzemelnyi.todolist.databinding.FragmentUpdateBinding
import com.gmail.konstantin.bezzemelnyi.todolist.fragments.SharedViewModel


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.todoUpdating = args.currentItem

        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
//        binding.currentPrioritiesSpinner.setSelection(mSharedViewModel.parsePriority(args.currentItem.priority))

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem() {
        removeItem()
    }

    private fun updateItem() {
        val title = binding.currentTitleEt.text.toString()
        val description = binding.currentDescriptionEt.text.toString()
        val priority = binding.currentPrioritiesSpinner.selectedItem.toString()

        if (!mSharedViewModel.userInputIsEmpty(title, description)) {
            val updatedItem = ToDoEntity(
                args.currentItem.id,
                title,
                mSharedViewModel.parsePriority(priority),
                description
            )
            mToDoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please, fill in all the fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun removeItem() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Delete '${args.currentItem.title}'")
            setMessage("Are you sure you want to delete this Todo?")
            setPositiveButton("Yes") { _, _ ->
                mToDoViewModel.deleteData(args.currentItem)
                Toast.makeText(
                    requireContext(),
                    "Successfully removed '${args.currentItem.title}'",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            setNegativeButton("No") { _, _ -> }

            show()
        }


    }
}