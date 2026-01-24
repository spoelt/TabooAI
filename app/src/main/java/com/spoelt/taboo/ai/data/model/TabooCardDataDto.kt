package com.spoelt.taboo.ai.data.model

import com.spoelt.taboo.ai.domain.model.TabooCardData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TabooCardDataDto(
    val id: Int,
    val word: String,
    @Json(name = "forbidden_words") val  forbiddenWords: List<String>,
)

fun TabooCardDataDto.toDomain() = TabooCardData(
    id = this.id,
    word = this.word,
    forbiddenWords = this.forbiddenWords,
)

fun List<TabooCardDataDto>.toDomainList() = map { it.toDomain() }