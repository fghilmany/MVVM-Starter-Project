package com.fghilmany.mvvmstarterproject.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class EmailResponse(

	@field:SerializedName("data")
	val data: List<Any>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
