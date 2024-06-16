package com.amoferreira.dictionary.data.repository

import android.content.Context
import com.amoferreira.dictionary.data.source.local.dao.WordInfoDao
import com.amoferreira.dictionary.data.source.remote.DictionaryApi
import com.amoferreira.dictionary.domain.repository.WordInfoRepository
import io.github.serpro69.kfaker.Faker
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.After
import org.junit.Before

class WordInfoRepositoryTest {
    private lateinit var contextMock: Context
    private lateinit var apiMock: DictionaryApi
    private lateinit var daoMock: WordInfoDao
    private lateinit var sut: WordInfoRepository

    private val faker by lazy { Faker() }

    @Before
    fun setup() {
        contextMock = mockk()
        apiMock = mockk()
        daoMock = mockk()
        sut = WordInfoRepositoryImpl(contextMock, apiMock, daoMock)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    /*
    * Test 1 -> exception 1
    * Test2 -> exception 2*/
}