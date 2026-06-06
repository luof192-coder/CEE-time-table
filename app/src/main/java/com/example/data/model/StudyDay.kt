package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_days")
data class StudyDay(
    @PrimaryKey val dayNumber: Int,
    val phase: String,
    val physics: String,
    val chemistry: String,
    val zoology: String,
    val botany: String,
    val mat: String,
    val isCompleted: Boolean = false,
    val notes: String = ""
)
