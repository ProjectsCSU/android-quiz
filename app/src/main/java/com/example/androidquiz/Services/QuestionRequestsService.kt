package com.example.androidquiz.Services

import com.example.androidquiz.Dtos.QuestionDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class QuestionRequestsService {
    companion object {
        const val QUIZ_DOMAIN = "https://opentdb.com/api.php?"
    }
    fun getQuestions(amount : String, category : String, difficulty : String) : String {
        val modifiedUrl = QUIZ_DOMAIN + "amount=$amount&category=$category&difficulty=$difficulty&type=multiple"
        return URL(modifiedUrl).openStream()
            .bufferedReader()
            .use { it.readText() }
    }
    fun serializeQuestions(response: String): QuestionDto {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(response)
    }
}