package com.example.helderrocha.kanaproject.view.pull_request

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.helderrocha.kanaproject.R
import com.example.helderrocha.kanaproject.model.PullRequest
import com.example.helderrocha.kanaproject.view.pull_request.adapter.PullAdapter
import com.example.helderrocha.kanaproject.view_model.PullRequestsViewModel
import com.example.helderrocha.kanaproject.view_model.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.pull_request_activity.*
import javax.inject.Inject

class PullRequestActivity : AppCompatActivity() {
    @Inject
    lateinit var pullsVMFactory: ViewModelFactory<PullRequestsViewModel>

    private val pullsViewModel by lazy {
        ViewModelProviders.of(this, pullsVMFactory)[PullRequestsViewModel::class.java]
    }

    protected val ItemsObserver = Observer<List<PullRequest>>(::onItemsFetched)
    private lateinit var adapter: PullAdapter

    var layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pull_request_activity)

        recyclerViewPullRequests.layoutManager = layoutManager
        recyclerViewPullRequests.setHasFixedSize(true)
        val name = intent.getStringExtra("name")
        val repo = intent.getStringExtra("repo")
        pullsViewModel.pulls.observe(this, ItemsObserver)
        pullsViewModel.getPullRequest(name, repo)
    }

    private fun onItemsFetched(pullRequests: List<PullRequest>?) {
        Log.i("Helder", pullRequests!![0].title)
        adapter = PullAdapter(pullRequests!!, { pullRequest: PullRequest -> partItemClicked(pullRequest) } )
        recyclerViewPullRequests.adapter = adapter
        progressBarPullRequest.visibility = View.GONE
    }

    private fun partItemClicked(pullRequest : PullRequest) {
        val uris = Uri.parse(pullRequest.html_url)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        applicationContext.startActivity(intents)
    }
}
