package com.sam.flowpractice.hilt

import com.sam.flowpractice.repository.datasourse.DataSource
import com.sam.flowpractice.repository.datasourse.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class DataSourseModule {
    @Provides
    fun providerLocalDataSource(): DataSource {
        return LocalDataSource()
    }
}