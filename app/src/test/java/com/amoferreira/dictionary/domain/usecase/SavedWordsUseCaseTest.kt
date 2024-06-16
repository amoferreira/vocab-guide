package com.amoferreira.dictionary.domain.usecase

import com.amoferreira.dictionary.domain.repository.SavedWordsRepository
import com.amoferreira.dictionary.testUtils.mockLog
import com.amoferreira.dictionary.utils.Resource
import io.github.serpro69.kfaker.Faker
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class SavedWordsUseCaseTest {
    private lateinit var savedWordsRepositoryMock: SavedWordsRepository
    private lateinit var sut: SavedWordsUseCase
    private val savedWordsMockFlow = flowOf(Resource.Success(emptyList<String>()))

    private val faker by lazy { Faker() }

    @Before
    fun setup() {
        mockLog()
        savedWordsRepositoryMock = mockk(relaxed = true)
        every { savedWordsRepositoryMock.savedWords } returns savedWordsMockFlow
        sut = SavedWordsUseCaseImpl(savedWordsRepositoryMock)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when add word then should add word to repository`() = runTest {
        //Arrange
        val expectedWord = faker.random.randomString()
        //Act
        sut.addWord(expectedWord)
        //Assert
        coVerify(exactly = 1) { savedWordsRepositoryMock.addWord(expectedWord) }
    }

    @Test
    fun `when remove word then should remove word from repository`() = runTest {
        //Arrange
        val expectedWord = faker.random.randomString()
        //Act
        sut.removeWord(expectedWord)
        //Assert
        coVerify(exactly = 1) { savedWordsRepositoryMock.removeWord(expectedWord) }
    }
}