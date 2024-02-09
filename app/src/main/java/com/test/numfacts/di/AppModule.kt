package com.test.numfacts.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.numfacts.mvvm.NumbersViewModel
import com.test.numfacts.services.NumService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(private val activity: AppCompatActivity) {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): NumService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl("http://numbersapi.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NumService::class.java)
    }

    @Provides
    @Singleton
    fun provideNumbersViewModel(factory: ViewModelProvider.Factory): NumbersViewModel {
        return ViewModelProvider(activity, factory)[NumbersViewModel::class.java]
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(retrofit: NumService): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(NumbersViewModel::class.java)) {
                    return NumbersViewModel(retrofit) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
