package com.amoferreira.dictionary.data.repository

import com.amoferreira.dictionary.data.source.local.dao.SavedWordsDao
import com.amoferreira.dictionary.data.source.local.entity.SavedWordEntity
import com.amoferreira.dictionary.domain.repository.SavedWordsRepository
import com.amoferreira.dictionary.testUtils.mockLog
import com.amoferreira.dictionary.utils.Resource
import io.github.serpro69.kfaker.Faker
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class SavedWordsRepositoryTest {
    private lateinit var daoMock: SavedWordsDao
    private lateinit var sut: SavedWordsRepository

    private val faker by lazy { Faker()  }

    @Before
    fun setup() {
        mockLog()
        daoMock = mockk(relaxed = true)
        sut = SavedWordsRepositoryImpl(daoMock)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when getting words then should emit load while getting`() = runTest {
        //Assert
        coEvery { daoMock.getAllWords() } returns flow {}
        //Act
        val result = sut.savedWords.first()
        //Assert
        assert(result is Resource.Loading)
    }

    @Test
    fun `when successfully gets words then should emit success resource with words`() = runTest {
        //Assert
        val expectedList = listOf(faker.random.randomString(), faker.random.randomString())
        coEvery { daoMock.getAllWords() } returns flowOf(expectedList)
        //Act
        val result = sut.savedWords.last()
        //Assert
        assert(result is Resource.Success)
        assertEquals(expectedList, result.data)
    }

    @Test
    fun `when error occur while getting words then should emit error with message`() = runTest {
        //Assert
        coEvery { daoMock.getAllWords() } throws Exception()
        //Act
        val result = sut.savedWords.last()
        //Assert
        assert(result is Resource.Error)
        assertEquals("Something went wrong", result.message)
    }

    @Test
    fun `when add word then should add word entity to dao`() = runTest {
        //Assert
        val expectedWord = faker.random.randomString()
        //Act
        sut.addWord(expectedWord)
        //Assert
        coVerify(exactly = 1) { daoMock.insertWord(SavedWordEntity(expectedWord)) }
    }

    @Test
    fun `when remove word then should remove word from dao`() = runTest {
        //Assert
        val expectedWord = faker.random.randomString()
        //Act
        sut.removeWord(expectedWord)
        //Assert
        coVerify(exactly = 1) { daoMock.deleteWord(expectedWord) }
    }
}