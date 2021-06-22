package com.cleanarchitectkotlinflowhiltsimplestway.domain

import com.google.gson.JsonObject
import com.cleanarchitectkotlinflowhiltsimplestway.data.APIs
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val apIs: APIs
) {
    suspend operator fun invoke(): JsonObject {
        val response = apIs.sampleGet()
        //here you can add some domain logic or call another UseCase
        return response
    }
}