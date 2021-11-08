package com.fghilmany.mvvmstarterproject.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fghilmany.mvvmstarterproject.core.data.DataRepository

class MainViewModel(val dataRepository: DataRepository): ViewModel() {

    private var _email = MutableLiveData<String>()
    var email get() = _email
    set(value) {
        _email.postValue(value.value)
    }

    fun getEmailOnline() = email.value?.let { dataRepository.getEmailOnline(it) }?.asLiveData()
}