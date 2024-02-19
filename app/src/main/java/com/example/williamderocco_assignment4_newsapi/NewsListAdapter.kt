package com.example.williamderocco_assignment4_newsapi

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.williamderocco_assignment4_newsapi.databinding.ListItemNewsBinding

class NewsHolder(
    private val binding: ListItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: News) {
        binding.newsTitle.text = news.title
        binding.newsDate.text = news.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${news.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class NewsListAdapter(
    private val newsList: List<News>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemNewsBinding.inflate(inflater, parent, false)

        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = newsList[position]
        when (holder) {
            is NewsHolder -> holder.bind(news)
        }
    }

    override fun getItemCount() = newsList.size

}
