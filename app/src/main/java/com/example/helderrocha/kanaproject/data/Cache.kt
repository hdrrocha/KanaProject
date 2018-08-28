package com.example.helderrocha.kanaproject.data

import com.example.helderrocha.kanaproject.model.Items
import com.example.helderrocha.kanaproject.model.PullRequest

object Cache {
    var repositories = listOf<Items>()

    fun cacheRepositories(repositories: List<Items>) {
        this.repositories = repositories
    }

    var pulls = listOf<PullRequest>()

    fun cachePulls(pulls: List<PullRequest>) {
        this.pulls = pulls
    }
}
