package com.example.androidquiz.stats.service

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.androidquiz.stats.model.StatisticsDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File

class StatisticsService() {
    constructor(context: AppCompatActivity) : this() {
        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ){
            val permissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(context, permissions,0)
        }
    }

    fun writeObjectToFile(obj: StatisticsDto, file: File) {
        val gson = jacksonObjectMapper()
        gson.writeValue(file, obj)
    }

    fun readObjectFromFile(file: File) : StatisticsDto? {
        val gson = jacksonObjectMapper()
        val json = file.readText()
        return gson.readValue(json, StatisticsDto::class.java)
    }

    fun initStatistics() : StatisticsDto {
        val defaultRating = mutableListOf(
            StatisticsDto.CategoryRating("Mythology", 0),
            StatisticsDto.CategoryRating("Geography", 0),
            StatisticsDto.CategoryRating("History", 0),
            StatisticsDto.CategoryRating("Art", 0),
            StatisticsDto.CategoryRating("Sports", 0),
            StatisticsDto.CategoryRating("Animals", 0),
        )
        return StatisticsDto(defaultRating)
    }
}