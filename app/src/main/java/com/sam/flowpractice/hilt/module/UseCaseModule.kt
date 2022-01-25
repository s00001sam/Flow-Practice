package com.sam.flowpractice.hilt.module
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class UseCaseModule {

//    @Provides
//    fun provideGetNearbyFoodsData(repository: Repository) = GetNearbyFoodsData(repository)
}
