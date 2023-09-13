package com.app.compose_structure.di

import com.app.compose_structure.domain.repository.IUserRepository
import com.app.compose_structure.domain.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun provideUserRepository(repository: UserRepositoryImpl): IUserRepository

}