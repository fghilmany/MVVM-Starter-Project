package com.fghilmany.mvvmstarterproject.core.data.remote.network

import com.fghilmany.mvvmstarterproject.core.data.remote.response.EmailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    //url is set in local.properties inside gradle folder
    //example get api with retrofit
    @GET
    suspend fun getEmail(
        @Query("email") email : String
    ): EmailResponse

}