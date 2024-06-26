package com.amoferreira.dictionary.di

import android.app.Application
import androidx.room.Room
import com.amoferreira.dictionary.data.repository.SavedWordsRepositoryImpl
import com.amoferreira.dictionary.data.source.local.SavedWordsDatabase
import com.amoferreira.dictionary.domain.repository.SavedWordsRepository
import com.amoferreira.dictionary.domain.usecase.SavedWordsUseCase
import com.amoferreira.dictionary.domain.usecase.SavedWordsUseCaseImpl
import com.amoferreira.dictionary.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SavedWordsModule {
    @Provides
    @Singleton
    fun provideGetSavedWordsUseCase(repository: SavedWordsRepository): SavedWordsUseCase {
        return SavedWordsUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideSavedWordsRepository(
        db: SavedWordsDatabase,
        resourceProvider: ResourceProvider
    ): SavedWordsRepository {
        return SavedWordsRepositoryImpl(db.dao, resourceProvider)
    }

    @Provides
    @Singleton
    fun provideSavedWordsDatabase(app: Application): SavedWordsDatabase {
        return Room
            .databaseBuilder(app, SavedWordsDatabase::class.java, "saved_words_db")
            .build()
    }
}