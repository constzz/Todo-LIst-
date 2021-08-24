package com.gmail.konstantin.bezzemelnyi.todolist.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.konstantin.bezzemelnyi.todolist.R
import com.gmail.konstantin.bezzemelnyi.todolist.common.extensions.swipeToDeleteForItems
import com.gmail.konstantin.bezzemelnyi.todolist.data.viewmodel.ToDoViewModel
import com.gmail.konstantin.bezzemelnyi.todolist.databinding.FragmentListBinding
import com.gmail.konstantin.bezzemelnyi.todolist.fragments.SharedViewModel
import com.gmail.konstantin.bezzemelnyi.todolist.fragments.list.adapter.ListAdapter
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.sharedViewModel = mSharedViewModel

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.swipeToDeleteForItems { viewHolderPosition ->
            val toDoEntityToDelete = adapter.getToDoEntity(viewHolderPosition)
            mToDoViewModel.deleteData(toDoEntityToDelete)
            adapter.notifyItemRemoved(viewHolderPosition)
            showUndoSnackbar(binding.root, binding.floatingActionButton)
        }

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        }) //notify on changes in database

        setHasOptionsMenu(true)

        return binding.root

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all -> deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Delete all todos")
            setMessage("Are you sure you want to remove all your todos?")
            setPositiveButton("Yes") { _, _ ->
                mToDoViewModel.deleteAll()
                Toast.makeText(
                    requireContext(),
                    "Go and create new todos!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            setNegativeButton("No") { _, _ -> }

            show()
        }

    }

    private fun showUndoSnackbar(view: View, anchorView: View?) {
        Snackbar
            .make(view, "ToDo was deleted", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                mToDoViewModel.restoreLatestDeletedData()
            }
            .setAnchorView(anchorView)
            .show()
    }

}