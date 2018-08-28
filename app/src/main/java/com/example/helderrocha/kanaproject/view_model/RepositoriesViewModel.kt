package com.example.helderrocha.kanaproject.view_model
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.helderrocha.kanaproject.api.ApiClient
import com.example.helderrocha.kanaproject.model.Items
import com.example.helderrocha.kanaproject.SchedulerProvider
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(val api: ApiClient, private val schedulers: SchedulerProvider) : ViewModel() {
    val _repositories = MutableLiveData<List<Items>>()
    val repositories: LiveData<List<Items>> = _repositories

    fun getRepositories(page: Long) {
        api.repositoriesscroll(page.toString())
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe({
                    _repositories.value = it.items
                }, {
                    _repositories.value = listOf()
                })
    }
}
