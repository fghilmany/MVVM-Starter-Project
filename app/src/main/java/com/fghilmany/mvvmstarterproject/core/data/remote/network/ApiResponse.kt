package com.fghilmany.mvvmstarterproject.core.data.remote.network

sealed class ApiResponse<out R> {
    data class Success<out T>(val body: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}