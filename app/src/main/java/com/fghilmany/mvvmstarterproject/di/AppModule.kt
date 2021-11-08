package com.fghilmany.mvvmstarterproject.di

import com.fghilmany.mvvmstarterproject.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}