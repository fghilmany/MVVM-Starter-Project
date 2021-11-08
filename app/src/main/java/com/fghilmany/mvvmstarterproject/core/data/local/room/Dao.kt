package com.fghilmany.mvvmstarterproject.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fghilmany.mvvmstarterproject.core.data.local.entity.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    //example query
    @Query("SELECT * FROM table_name")
    fun getEmail(): Flow<Entity>

    //example insert
    @Insert
    suspend fun insertEmail(entity: Entity)
}