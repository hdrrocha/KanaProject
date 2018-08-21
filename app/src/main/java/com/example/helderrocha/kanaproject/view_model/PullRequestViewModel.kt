package com.example.helderrocha.kanaproject.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.helderrocha.kanaproject.api.ApiClient
import com.example.helderrocha.kanaproject.SchedulerProvider
import com.example.helderrocha.kanaproject.model.PullRequest
import javax.inject.Inject

class PullRequestsViewModel @Inject constructor(val api: ApiClient, private val schedulers: SchedulerProvider) : ViewModel() {
    val _pulls = MutableLiveData<List<PullRequest>>()
    val pulls: LiveData<List<PullRequest>> = _pulls

    fun getPullRequest(name: String, repositorio: String) {

        api.pullRequest(name, repositorio)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe({
                    _pulls.value = it
                }, {
                    _pulls.value = listOf()
                    Log.e("Error", it.toString())
                })
    }

}
