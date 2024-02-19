package com.example.williamderocco_assignment4_newsapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.williamderocco_assignment4_newsapi.databinding.FragmentNewsDetailBinding
import com.example.williamderocco_assignment4_newsapi.databinding.FragmentNewsListBinding
import java.util.Date
import java.util.UUID
import kotlin.random.Random

class NewsDetailFragment : Fragment() {
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        crime = Crime(
//            id = UUID.randomUUID(),
//            title = "",
//            date = Date(),
//            isSolved = false,
//            requiresPolice = Random.nextBoolean()
//        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the news object from arguments
        val news = arguments?.getParcelable<News>("news")
        // Now you can use the `news` object as needed
        news?.let {
            // Access news properties and update UI
            binding.titleTextView.text = it.title
            binding.descriptionTextView.text = it.description
            binding.contentTextView.text = it.content
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}