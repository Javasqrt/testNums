package com.test.numfacts.di

import com.test.numfacts.MainActivity
import com.test.numfacts.mvvm.NumbersViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(viewModel: NumbersViewModel)
}