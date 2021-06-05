package com.gmail.konstantin.bezzemelnyi.todolist.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.konstantin.bezzemelnyi.todolist.R
import com.gmail.konstantin.bezzemelnyi.todolist.data.viewmodel.ToDoViewModel
import com.gmail.konstantin.bezzemelnyi.todolist.databinding.FragmentListBinding
import com.gmail.konstantin.bezzemelnyi.todolist.fragments.SharedViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mShareViewModel: SharedViewModel by viewModels()
    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mShareViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        }) //notify on changes in database

        mShareViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            showEmptyDatabase(it)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return binding.root

    }

    private fun showEmptyDatabase(emptyDatabase: Boolean) {
        if (emptyDatabase) {
            binding.noDataImageView.visibility = View.VISIBLE
            binding.noDataTextView.visibility = View.VISIBLE
        } else {
            binding.noDataImageView.visibility = View.INVISIBLE
            binding.noDataTextView.visibility = View.INVISIBLE
        }
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
                Toast.makeText(requireContext(),
                        "Go and create new todos!",
                        Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("No") { _, _ -> }

            show()
        }

    }
}