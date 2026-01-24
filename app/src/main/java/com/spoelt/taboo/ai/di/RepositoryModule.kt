package com.spoelt.taboo.ai.di

import com.spoelt.taboo.ai.data.repository.WordRepositoryImpl
import com.spoelt.taboo.ai.domain.repository.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWordRepository(
        impl: WordRepositoryImpl
    ): WordRepository
}