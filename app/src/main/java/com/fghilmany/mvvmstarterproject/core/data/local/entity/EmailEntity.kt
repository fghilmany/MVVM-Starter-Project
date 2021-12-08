package com.fghilmany.mvvmstarterproject.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fghilmany.mvvmstarterproject.core.utils.TABLE_NAME

@Entity(tableName = "table_name")
data class EmailEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    var email: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = ""
)