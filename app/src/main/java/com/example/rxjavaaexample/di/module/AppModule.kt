package com.example.rxjavaaexample.di.module

import android.content.Context
import com.example.rxjavaaexample.adapter.UserListAdapter
import com.example.rxjavaaexample.data.api.ApiServices
import com.example.rxjavaaexample.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class AppModule {
    @Provides
    fun provideRepository(apiServices: ApiServices) = Repository(apiServices)

    @Provides
    fun provideUserListAdapter(@ApplicationContext context: Context) = UserListAdapter(context)
}