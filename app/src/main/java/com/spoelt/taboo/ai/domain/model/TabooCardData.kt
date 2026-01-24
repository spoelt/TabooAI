package com.spoelt.taboo.ai.domain.model

data class TabooCardData(
    val id: Int,
    val word: String,
    val forbiddenWords: List<String>,
)
