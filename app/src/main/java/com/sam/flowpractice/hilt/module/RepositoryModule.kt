package com.sam.flowpractice.hilt

import com.sam.flowpractice.repository.BaseRepository
import com.sam.flowpractice.repository.datasourse.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideRepository(localDataSource: DataSource) = BaseRepository(localDataSource)
}