package com.spoelt.taboo.ai.domain.repository

import com.spoelt.taboo.ai.domain.model.TabooCardData
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun loadWords(): Flow<List<TabooCardData>>
}