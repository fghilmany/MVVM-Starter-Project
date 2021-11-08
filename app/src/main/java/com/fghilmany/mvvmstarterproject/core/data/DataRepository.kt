package com.fghilmany.mvvmstarterproject.core.data

import com.fghilmany.mvvmstarterproject.core.data.local.LocalDatasource
import com.fghilmany.mvvmstarterproject.core.data.local.entity.Entity
import com.fghilmany.mvvmstarterproject.core.data.remote.RemoteDatasource
import com.fghilmany.mvvmstarterproject.core.data.remote.network.ApiResponse
import com.fghilmany.mvvmstarterproject.core.data.remote.response.EmailResponse
import com.fghilmany.mvvmstarterproject.core.utils.PreferenceProvider
import kotlinx.coroutines.flow.Flow

class DataRepository (
    private val remoteDatasource: RemoteDatasource,
    private val localDatasource: LocalDatasource,
    private val preferenceProvider: PreferenceProvider
): IDataRepository {

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
    override fun getEmailOnlineOffline(email: String): Flow<Resource<Entity>> {
        return object : NetworkBoundResource<Entity, EmailResponse>(){
            override fun loadFromDB(): Flow<Entity> {
                return localDatasource.getEmail()
            }

            override fun shouldFetch(data: Entity?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<EmailResponse>> {
                return remoteDatasource.getEmail(email)
            }

            override suspend fun saveCallResult(data: EmailResponse) {
                val mapping = Entity(
                    data.message?:""
                )
                localDatasource.insertEmail(mapping)
            }

        }.asFlow()
    }

    // get offline
    override fun getEmailOffline(): Flow<Entity> {
        return localDatasource.getEmail()
    }

    // insert email offline
    override suspend fun insertEmailOffline(entity: Entity) {
        localDatasource.insertEmail(entity)
    }


}