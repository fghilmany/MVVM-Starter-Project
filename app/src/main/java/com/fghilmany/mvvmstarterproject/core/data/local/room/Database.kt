package com.fghilmany.mvvmstarterproject.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fghilmany.mvvmstarterproject.core.data.local.entity.EmailEntity
import com.fghilmany.mvvmstarterproject.core.utils.DATABASE_VERSION

@Database(
    entities = [EmailEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class Database : RoomDatabase(){
    abstract fun dao(): Dao
}