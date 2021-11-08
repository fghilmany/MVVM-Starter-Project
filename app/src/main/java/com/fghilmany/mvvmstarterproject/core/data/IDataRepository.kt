package com.fghilmany.mvvmstarterproject.core.data

import com.fghilmany.mvvmstarterproject.core.data.local.entity.Entity
import com.fghilmany.mvvmstarterproject.core.data.remote.response.EmailResponse
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    //get online
    fun getEmailOnline(email: String): Flow<Resource<EmailResponse>>

    //get online offline
    fun getEmailOnlineOffline(email: String): Flow<Resource<Entity>>

    //get offline
    fun getEmailOffline(): Flow<Entity>

    //insert email offline
    suspend fun insertEmailOffline(entity: Entity)


}