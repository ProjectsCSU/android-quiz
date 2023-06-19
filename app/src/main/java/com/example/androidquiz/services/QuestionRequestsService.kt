package com.example.androidquiz.services

import com.example.androidquiz.dtos.QuestionDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.URL

class QuestionRequestsService {
    companion object {
        const val QUIZ_DOMAIN = "https://opentdb.com/api.php?"
    }

    fun getQuestions(amount: String, category: String, difficulty: String): Deferred<String> =
        CoroutineScope(Dispatchers.IO).async {
            val modifiedUrl =
                QUIZ_DOMAIN + "amount=$amount&category=$category&difficulty=$difficulty&type=multiple"
            URL(modifiedUrl).openStream()
                .bufferedReader()
                .use { it.readText() }
        }
    fun serializeQuestions(response: String): QuestionDto {
        val mapper = jacksonObjectMapper()
        val result: QuestionDto = mapper.readValue(response)
        if(result.results == null) {
            throw Exception("Unable to get questions. Please, try again later...")
        }
        return result
    }
}