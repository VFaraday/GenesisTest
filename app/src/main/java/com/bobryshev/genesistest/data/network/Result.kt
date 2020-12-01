package com.bobryshev.genesistest.data.network

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val exception : String?) : Result<Nothing>()
}