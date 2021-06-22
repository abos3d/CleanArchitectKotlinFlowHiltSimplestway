package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import androidx.lifecycle.ViewModel
import com.cleanarchitectkotlinflowhiltsimplestway.domain.SampleUseCase
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import com.cleanarchitectkotlinflowhiltsimplestway.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(private val sampleUseCase: SampleUseCase) :
    ViewModel() {

    fun getSampleResponse() = flow {
        emit(State.LoadingState)
        try {
            delay(1000)
            emit(State.DataState(sampleUseCase()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Utils.resolveError(e))
        }
    }
}