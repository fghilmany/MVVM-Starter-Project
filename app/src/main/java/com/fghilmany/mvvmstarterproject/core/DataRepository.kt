package com.fghilmany.mvvmstarterproject.core

import com.fghilmany.mvvmstarterproject.core.data.local.LocalDatasource
import com.fghilmany.mvvmstarterproject.core.data.local.entity.Entity
import com.fghilmany.mvvmstarterproject.core.data.remote.RemoteDatasource
import com.fghilmany.mvvmstarterproject.core.data.remote.network.ApiResponse
import com.fghilmany.mvvmstarterproject.core.data.remote.response.EmailResponse
import com.fghilmany.mvvmstarterproject.utils.PreferenceProvider
import kotlinx.coroutines.flow.Flow

class DataRepository (
    private val remoteDatasource: RemoteDatasource,
    private val localDatasource: LocalDatasource,
    private val preferenceProvider: PreferenceProvider
    ): IDataRepository{

    //get online
    override fun getEmailOnline(email: String): Flow<Resource<EmailResponse>> {
        return object : OnlineBoundResource<EmailResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<EmailResponse>> {
                return remoteDatasource.getEmail(email)
            }

            override fun getResponse(data: EmailResponse) {
                // if you want get or save response, make it here
            }
        }.asFlow()
    }

    // get online offline
    override fun getEmailOnlineOffline(): Flow<Resource<Entity>> {

    }

    // get offline
    override fun getEmailOffline(): Flow<Resource<Entity>> {

    }

    // insert email offline
    override suspend fun insertEmailOffline(entity: Entity) {

    }


}