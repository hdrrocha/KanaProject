package com.example.helderrocha.kanaproject.view.pull_request

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.helderrocha.kanaproject.R
import dagger.android.AndroidInjection

class PullRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pull_request_activity)
    }
}
