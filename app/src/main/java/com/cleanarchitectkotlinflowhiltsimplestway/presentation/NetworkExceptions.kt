package com.cleanarchitectkotlinflowhiltsimplestway.presentation

import org.json.JSONObject
import retrofit2.HttpException

open class NetworkErrorException(
    val errorCode: Int = -1,
    val errorMessage: String,
    val response: String = ""
) : Exception() {
    override val message: String
        get() = localizedMessage

    override fun getLocalizedMessage(): String {
        return errorMessage
    }

    companion object {
        fun parseException(e: HttpException): NetworkErrorException {
            val errorBody = e.response()?.errorBody()?.string()

            return try {//here you can parse the error body that comes from server
                NetworkErrorException(e.code(), JSONObject(errorBody!!).getString("message"))
            } catch (_: Exception) {
                NetworkErrorException(e.code(), "unexpected error!!ً")
            }
        }
    }
}

class AuthenticationException(authMessage: String) :
    NetworkErrorException(errorMessage = authMessage) {}