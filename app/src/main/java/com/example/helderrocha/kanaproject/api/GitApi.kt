package com.example.helderrocha.kanaproject.api

import com.example.helderrocha.kanaproject.model.PullRequest
import com.example.helderrocha.kanaproject.model.RepositoryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApi {
    companion object {
        const val URL = "https://api.github.com/"
    }

    @GET("search/repositories?q=language:Java&sort=stars&")
    fun repositoriesscroll(@Query("page") page: String): Observable<RepositoryResponse>

    @GET("/repos/{name}/{repo}/pulls" )
    fun pullRequest(
            @Path("name") name: String,
            @Path("repo") repo: String): Observable<List<PullRequest>>

}