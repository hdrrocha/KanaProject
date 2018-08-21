package com.example.helderrocha.kanaproject.view.repositories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.helderrocha.kanaproject.R
import com.example.helderrocha.kanaproject.model.Items
import com.example.helderrocha.kanaproject.view.pull_request.PullRequestActivity
import com.example.helderrocha.kanaproject.view.repositories.adapter.RepositoryAdapter
import com.example.helderrocha.kanaproject.view_model.RepositoriesViewModel
import com.example.helderrocha.kanaproject.view_model.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.repository_list_activity.*
import javax.inject.Inject

class RepositoryListActivity : AppCompatActivity() {
    @Inject
    lateinit var itemsVMFactory: ViewModelFactory<RepositoriesViewModel>

    private val itemsViewModel by lazy {
        ViewModelProviders.of(this, itemsVMFactory)[RepositoriesViewModel::class.java]
    }

    protected val ItemsObserver = Observer<List<Items>>(::onItemsFetched)
    private lateinit var adapter: RepositoryAdapter
    var layoutManager = LinearLayoutManager(this)
    var totalItemCount: Int = 0
    var visibleItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var loading: Boolean = false
    var page: Long = 1L


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_list_activity)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        itemsViewModel.repositories.observe(this, ItemsObserver)
        itemsViewModel.getRepositories(page)
    }

    private fun onItemsFetched(newItems: List<Items>?) {
        if (newItems != null) {
            loading = true
            setUpdateAdapter(newItems)
        } else {
            Toast.makeText(this, "There is no", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setUpdateAdapter(items: List<Items>){
        adapter = RepositoryAdapter(items, { item: Items -> partItemClicked(item) } )
        recyclerView.adapter = adapter
        progressBar.visibility = View.GONE
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    var total = visibleItemCount+ pastVisibleItemCount
                    pastVisibleItemCount =(recyclerView!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if(loading){
                        if((visibleItemCount+ pastVisibleItemCount) >= totalItemCount) {
                            progressBar.visibility = View.VISIBLE
                            loading = false
                            page++
                            itemsViewModel.getRepositories( page++)
                        }
                    }
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
    private fun partItemClicked(item : Items) {
        val str1 = item.owner.login.toLowerCase()
        val str3 = item.name.toLowerCase()

        val intent = Intent(this,PullRequestActivity::class.java)
        intent.putExtra("name",str1)
        intent.putExtra("repo",str3)
        startActivity(intent)
    }
}
