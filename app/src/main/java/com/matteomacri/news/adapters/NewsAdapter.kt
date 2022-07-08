package com.matteomacri.news.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matteomacri.news.R
import com.matteomacri.news.databinding.ItemNewsBinding
import com.matteomacri.news.models.New

class NewsAdapter(val context: Context, val newsList: List<New>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(newsList[position].url)
                startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = newsList[position].title
        holder.binding.tvDescription.text = newsList[position].description
        Glide.with(context)
            .load(newsList[position].image)
            .into(holder.binding.ivThumbnail)
    }

    override fun getItemCount() = newsList.size
}