package com.amoferreira.dictionary.testUtils

import com.amoferreira.dictionary.domain.model.Definition
import com.amoferreira.dictionary.domain.model.Meaning
import com.amoferreira.dictionary.domain.model.WordInfo
import io.github.serpro69.kfaker.Faker

val faker = Faker()

fun createDefinition(
    antonyms: List<String> = listOf(faker.random.randomString()),
    definition: String = faker.random.randomString(),
    example: String? = faker.random.randomString(),
    synonyms: List<String> = listOf(faker.random.randomString())
): Definition = Definition(
    antonyms = antonyms,
    definition = definition,
    example = example,
    synonyms = synonyms
)

fun createMeaning(
    definitions: List<Definition> = listOf(createDefinition()),
    partOfSpeech: String = faker.random.randomString()
): Meaning = Meaning(
    definitions = definitions,
    partOfSpeech = partOfSpeech
)

fun createWordInfo(
    meanings: List<Meaning> = listOf(createMeaning()),
    phonetic: String = faker.random.randomString(),
    word: String = faker.random.randomString()
): WordInfo = WordInfo(
    meanings = meanings,
    phonetic = phonetic,
    word = word
)

