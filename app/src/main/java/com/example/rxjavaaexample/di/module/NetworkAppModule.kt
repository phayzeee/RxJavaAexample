package com.example.rxjavaaexample.di.module

import com.example.rxjavaaexample.data.api.ApiServices
import com.example.rxjavaaexample.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object NetworkAppModule {
    @Provides
    fun provideServiceBuilder(): ApiServices {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
}