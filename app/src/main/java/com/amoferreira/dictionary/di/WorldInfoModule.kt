package com.amoferreira.dictionary.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.amoferreira.dictionary.data.repository.WordInfoRepositoryImpl
import com.amoferreira.dictionary.data.source.local.Converters
import com.amoferreira.dictionary.data.source.local.WordInfoDatabase
import com.amoferreira.dictionary.data.source.remote.DictionaryApi
import com.amoferreira.dictionary.data.utils.GsonParser
import com.amoferreira.dictionary.domain.repository.WordInfoRepository
import com.amoferreira.dictionary.domain.usecase.GetWordInfoUseCase
import com.amoferreira.dictionary.domain.usecase.GetWordInfoUseCaseImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorldInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfoUseCase {
        return GetWordInfoUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        @ApplicationContext appContext: Context,
        api: DictionaryApi,
        db: WordInfoDatabase
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(appContext, api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room
            .databaseBuilder(app, WordInfoDatabase::class.java, "word_db")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}