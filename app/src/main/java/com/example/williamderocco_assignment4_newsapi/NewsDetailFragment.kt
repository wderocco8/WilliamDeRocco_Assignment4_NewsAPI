package com.example.williamderocco_assignment4_newsapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.williamderocco_assignment4_newsapi.databinding.FragmentNewsDetailBinding
import com.bumptech.glide.Glide

private const val TAG = "NewsDetailFragment"

class NewsDetailFragment : Fragment() {
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
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

        // Retrieve individual parameters from arguments
        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val content = arguments?.getString("content")
        val imageUrl = arguments?.getString("imageUrl")

        // Now you can use these parameters to update your UI
        title?.let { binding.titleTextView.text = it }
        description?.let { binding.descriptionTextView.text = it }
        content?.let { binding.contentTextView.text = it }
        Log.d(TAG, "imageURL: " + imageUrl)
        imageUrl?.let {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.place_holder)
                .into(binding.newsImageView)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}