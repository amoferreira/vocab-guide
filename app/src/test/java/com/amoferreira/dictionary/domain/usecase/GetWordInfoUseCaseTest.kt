package com.amoferreira.dictionary.domain.usecase

import com.amoferreira.dictionary.domain.repository.WordInfoRepository
import com.amoferreira.dictionary.testUtils.createWordInfo
import com.amoferreira.dictionary.utils.Resource
import io.github.serpro69.kfaker.Faker
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetWordInfoUseCaseTest {
    private lateinit var repositoryMock: WordInfoRepository
    private lateinit var sut: GetWordInfoUseCase

    private val faker by lazy { Faker() }

    @Before
    fun setup() {
        repositoryMock = mockk()
        sut = GetWordInfoUseCaseImpl(repositoryMock)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when word is blank then should return empty flow`() = runTest {
        //Arrange
        val word = " "
        //Act
        val result = sut.getWordInfo(word).firstOrNull()
        //Assert
        assertEquals(null, result)
    }

    @Test
    fun `when word is not blank then should return repository answer`() = runTest {
        //Arrange
        val word = faker.random.randomString()
        val expectedResult = Resource.Success(data = listOf(createWordInfo()))
        every {
            repositoryMock.getWordInfo(word)
        } returns flowOf(expectedResult)
        //Act
        val result = sut.getWordInfo(word).firstOrNull()
        //Assert
        assertEquals(expectedResult, result)
    }
}