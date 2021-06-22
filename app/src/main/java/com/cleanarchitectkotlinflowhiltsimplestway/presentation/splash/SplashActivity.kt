package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val textView = findViewById<TextView>(R.id.textView1)

        lifecycleScope.launch {
            delay(500)
            viewModel.getSampleResponse()
                .collect {
                    when (it) {
                        is State.DataState -> textView.text = "success ${it.data}"
                        is State.ErrorState -> textView.text = "error ${it.exception}"
                        is State.LoadingState -> textView.text = "loading"
                    }

                }
        }
    }
}