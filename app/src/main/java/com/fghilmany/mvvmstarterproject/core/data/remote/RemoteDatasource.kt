package com.fghilmany.mvvmstarterproject.core.data.remote

import android.content.Context
import com.fghilmany.mvvmstarterproject.R
import com.fghilmany.mvvmstarterproject.core.data.remote.network.ApiResponse
import com.fghilmany.mvvmstarterproject.core.data.remote.network.ApiServices
import com.fghilmany.mvvmstarterproject.core.data.remote.response.EmailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.lang.Exception
import java.net.SocketTimeoutException

//use `context` if you want to use resource.getString() for call a message error or whatever
class RemoteDatasource(
    private val apiServices: ApiServices?,
    private val context: Context
) {
    //example get email
    suspend fun getEmail(email: String): Flow<ApiResponse<EmailResponse>> {
        return flow {
            try {
                val response = apiServices?.getEmail(email)
                if (response != null){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                when(e){
                    is SocketTimeoutException -> {
                        emit(ApiResponse.Error("Connection time out"))
                        Timber.tag("getEmail").e(e.message.toString())
                    }

                    is HttpException -> {
                        //if `message` key there or not, use try{} catch(){}
                        try {
                            val `object` = JSONObject(e.response()?.errorBody()?.string().toString())
                            val messageString: String = `object`.getString("message")
                            emit(ApiResponse.Error(messageString))
                            Timber.tag("getEmail").e(messageString)
                        }catch(e: Exception){
                            val messageString = "Something Wrong"
                            emit(ApiResponse.Error(messageString))
                            Timber.tag("getEmail").e(messageString)
                        }
                    }

                    else -> {
                        val messageString = "Something Wrong"
                        emit(ApiResponse.Error(messageString))
                        Timber.tag("getEmail").e(messageString)
                    }
                }
            }
        }
    }
}