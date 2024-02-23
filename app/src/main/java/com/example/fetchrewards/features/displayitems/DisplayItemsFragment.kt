package com.example.fetchrewards.features.displayitems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.R
import com.example.fetchrewards.domain.util.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DisplayItemsFragment : Fragment() {

    private val viewModel: DisplayItemsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var parentListAdapter: ParentListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display_items, container, false)
        bindUI(view)
        parentListAdapter = ParentListAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = parentListAdapter
        }

        bindCollector()
        return view
    }

    private fun bindCollector() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemList.collect {
                    when (it) {
                        is Response.Error -> {
                            Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                            progressBar.visibility = View.GONE
                        }

                        is Response.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }

                        is Response.Success -> {
                            it.data?.let { itemList ->
                                val pairList = itemList.groupBy { item ->
                                    item.listId
                                }.toSortedMap().toList()
                                parentListAdapter.submitList(pairList)
                            }
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun bindUI(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view)
        progressBar = view.findViewById(R.id.progressBar)
    }
}