package com.example.helderrocha.kanaproject.view.pull_request.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.helderrocha.kanaproject.R
import com.example.helderrocha.kanaproject.model.PullRequest
import kotlinx.android.synthetic.main.pull_item.view.*
import kotlinx.android.synthetic.main.repository_item.view.*

class PullAdapter (var pullRequests: List<PullRequest>, val clickListener: (PullRequest) -> Unit) : RecyclerView.Adapter<PullAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: PullRequest, clickListener: (PullRequest) -> Unit) {
            itemView.pullTitle.text = item.title
            itemView.pullBody.text = item.body
            itemView.pullUserName.text = item.user.login
            itemView.setOnClickListener { clickListener(item)}
            Glide.with(itemView)
                    .load(item.user.avatar_url)
                    .into(itemView.pullUserAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pull_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pullRequests.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        (holder as ViewHolder).bind(pullRequests[position], clickListener)
    }
}
