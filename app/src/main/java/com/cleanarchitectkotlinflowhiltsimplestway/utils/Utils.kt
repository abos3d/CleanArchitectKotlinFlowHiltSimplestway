package com.cleanarchitectkotlinflowhiltsimplestway.utils

import com.cleanarchitectkotlinflowhiltsimplestway.presentation.AuthenticationException
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.NetworkErrorException
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Utils {
    companion object{
        fun resolveError(e: Exception): State.ErrorState {
            var error = e

            when (e) {
                is SocketTimeoutException -> {
                    error = NetworkErrorException(errorMessage = "connection error!")
                }
                is ConnectException -> {
                    error = NetworkErrorException(errorMessage = "no internet access!")
                }
                is UnknownHostException -> {
                    error = NetworkErrorException(errorMessage = "no internet access!")
                }
            }

            if(e is HttpException){
                when(e.code()){
                    502 -> {
                        error = NetworkErrorException(e.code(),  "internal error!")
                    }
                    401 -> {
                        throw AuthenticationException("authentication error!")
                    }
                    400 -> {
                        error = NetworkErrorException.parseException(e)
                    }
                }
            }


            return State.ErrorState(error)
        }
    }
}