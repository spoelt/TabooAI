package com.spoelt.taboo.ai.data.repository

import android.content.Context
import com.spoelt.taboo.ai.data.util.loadJsonFromAssets
import com.spoelt.taboo.ai.data.model.TabooCardDataDto
import com.spoelt.taboo.ai.data.model.toDomainList
import com.spoelt.taboo.ai.domain.model.TabooCardData
import com.spoelt.taboo.ai.domain.repository.WordRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(private val context: Context) :
    WordRepository {

    private val moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, TabooCardDataDto::class.java)
    private val adapter = moshi.adapter<List<TabooCardDataDto>>(listType)

    override fun loadWords(): Flow<List<TabooCardData>> = flow {
        val jsonString = loadJsonFromAssets(context, "words.json")
        val wordList = adapter.fromJson(jsonString) ?: emptyList()
        emit(wordList.toDomainList())
    }
}