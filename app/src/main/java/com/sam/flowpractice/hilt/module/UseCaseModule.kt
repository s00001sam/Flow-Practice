package com.sam.flowpractice.hilt.module
import com.sam.flowpractice.hilt.usecase.GetDouble
import com.sam.flowpractice.hilt.usecase.GetMutliInt
import com.sam.flowpractice.hilt.usecase.GetSingleString
import com.sam.flowpractice.repository.BaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideGetSingleStr(repository: BaseRepository) = GetSingleString(repository)

    @Provides
    fun provideGetMutliInt(repository: BaseRepository) = GetMutliInt(repository)

    @Provides
    fun provideGetDouble(repository: BaseRepository) = GetDouble(repository)
}
