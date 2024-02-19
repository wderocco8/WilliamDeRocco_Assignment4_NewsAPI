package com.example.williamderocco_assignment4_newsapi

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.williamderocco_assignment4_newsapi.databinding.FragmentNewsListBinding

private const val TAG = "NewsListFragment"

class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val newsListViewModel: NewsListViewModel by viewModels()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d(TAG, "Total crimes: ${newsListViewModel.newsList.size}")
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)

        // Initialize RecyclerView
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NewsListAdapter(emptyList())
        binding.newsRecyclerView.adapter = adapter

        // Initialize Spinner
        val categorySpinner = binding.categorySpinner
        val categoryList = listOf("business", "entertainment", "general", "health", "science", "sports", "technology") // Example category list
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, categoryList)
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter

        // Spinner item selection listener
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = categoryList[position]
                newsListViewModel.fetchNewsByCategory(selectedCategory)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }


        // Observe changes to the newsList LiveData
        newsListViewModel.newsList.observe(viewLifecycleOwner) { newsList ->
            adapter.updateNewsList(newsList)
        }


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}