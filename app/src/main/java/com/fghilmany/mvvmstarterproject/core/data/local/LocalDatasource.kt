package com.fghilmany.mvvmstarterproject.core.data.local

import com.fghilmany.mvvmstarterproject.core.data.local.entity.Entity
import com.fghilmany.mvvmstarterproject.core.data.local.room.Dao

class LocalDatasource(
    private val dao: Dao
) {
    fun getEmail() = dao.getEmail()
    suspend fun insertEmail(entity: Entity) = dao.insertEmail(entity)
}