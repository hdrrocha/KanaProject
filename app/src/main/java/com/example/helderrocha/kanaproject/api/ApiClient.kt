package com.example.helderrocha.kanaproject.api
import com.example.helderrocha.kanaproject.model.PullRequest
import com.example.helderrocha.kanaproject.model.RepositoryResponse
import io.reactivex.Observable
import javax.inject.Inject

class ApiClient @Inject constructor(private val gitApi: GitApi) {
    fun repositoriesscroll(page: String): Observable<RepositoryResponse> {
        return gitApi.repositoriesscroll(page)
    }

    fun pullRequest(name: String, repositorio: String): Observable<List<PullRequest>> {
        return gitApi.pullRequest(name, repositorio)
    }

}