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
                exceptionLog(e, "getEmail")
            }
        }
    }

    private fun exceptionLog(e: Exception, tagLog: String): ApiResponse.Error {
        val tag = this::class.java.simpleName

        when (e) {
            is SocketTimeoutException -> {
                Timber.tag(tagLog).e(e.message.toString())
                return (ApiResponse.Error(
                    e.message.toString() + ", " + context.resources.getString(
                        R.string.check_your_internet_connection
                    )
                ))
            }

            is HttpException -> {
                return try {
                    val `object` = JSONObject(e.response()?.errorBody()?.string().toString())
                    val messageString: String = `object`.getString("message")
                    Timber.tag(tagLog).e(messageString)
                    (ApiResponse.Error(messageString))
                } catch (e: Exception) {
                    val messageString = context.resources.getString(R.string.something_wrong)
                    Timber.tag(tagLog).e(messageString)
                    (ApiResponse.Error(messageString))
                }
            }

            is NoSuchElementException -> {
                Timber.tag(tagLog).e(e.message.toString())
                return (ApiResponse.Error(
                    e.message.toString() + ", " + context.resources.getString(
                        R.string.check_your_internet_connection
                    )
                ))
            }

            else -> {
                val messageString = context.resources.getString(R.string.something_wrong)
                Timber.tag(tagLog).e(e)
                return (ApiResponse.Error(messageString))
            }
        }
    }

}