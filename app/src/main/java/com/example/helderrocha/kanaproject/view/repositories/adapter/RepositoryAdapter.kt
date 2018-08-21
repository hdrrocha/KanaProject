package com.example.helderrocha.kanaproject.view.repositories.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.helderrocha.kanaproject.R
import com.example.helderrocha.kanaproject.model.Items
import kotlinx.android.synthetic.main.repository_item.view.*


class RepositoryAdapter(var repository: List<Items>, val clickListener: (Items) -> Unit) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Items, clickListener: (Items) -> Unit) {
            itemView.repositoryTitle.text = item.name
            itemView.repositoryDescription.text = item.description
            itemView.repositoryStars.text = item.stargazers_count
            itemView.repositoryForks.text = item.forks
            itemView.repositoryUserName.text = item.owner.login
            itemView.repositoryName.text = item.name
            itemView.setOnClickListener { clickListener(item)}
            Glide.with(itemView)
                    .load(item.owner.avatar_url)
                    .into(itemView.imageUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = repository.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        (holder as ViewHolder).bind(repository[position], clickListener)
    }
}
