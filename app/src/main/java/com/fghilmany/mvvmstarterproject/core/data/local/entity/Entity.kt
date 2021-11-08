package com.fghilmany.mvvmstarterproject.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_name")
data class Entity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    var email: String
)