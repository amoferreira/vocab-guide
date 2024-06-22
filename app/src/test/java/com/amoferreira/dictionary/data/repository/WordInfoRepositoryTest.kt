package com.amoferreira.dictionary.data.repository

import com.amoferreira.dictionary.data.source.local.dao.WordInfoDao
import com.amoferreira.dictionary.data.source.remote.DictionaryApi
import com.amoferreira.dictionary.domain.repository.WordInfoRepository
import com.amoferreira.dictionary.utils.ResourceProvider
import io.github.serpro69.kfaker.Faker
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.After
import org.junit.Before

class WordInfoRepositoryTest {
    private lateinit var resourceProviderMock: ResourceProvider
    private lateinit var apiMock: DictionaryApi
    private lateinit var daoMock: WordInfoDao
    private lateinit var sut: WordInfoRepository

    private val faker by lazy { Faker() }

    @Before
    fun setup() {
        resourceProviderMock = mockk()
        apiMock = mockk()
        daoMock = mockk()
        sut = WordInfoRepositoryImpl(apiMock, daoMock, resourceProviderMock)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    /*
    * Test 1 -> exception 1
    * Test2 -> exception 2*/
}