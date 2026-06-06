package com.example.data.repository

import com.example.data.db.InitialData
import com.example.data.db.StudyDayDao
import com.example.data.model.StudyDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class StudyRepository(private val studyDayDao: StudyDayDao) {

    val allDays: Flow<List<StudyDay>> = studyDayDao.getAllDays()

    fun getDayByNumber(dayNumber: Int): Flow<StudyDay?> {
        return studyDayDao.getDayByNumberFlow(dayNumber)
    }

    suspend fun updateCompletion(dayNumber: Int, isCompleted: Boolean) {
        studyDayDao.updateCompletionStatus(dayNumber, isCompleted)
    }

    suspend fun updateNotes(dayNumber: Int, notes: String) {
        studyDayDao.updateDayNotes(dayNumber, notes)
    }

    suspend fun ensureDatabaseSeeded() {
        // Query the first elements of the list to see if the database is already populated.
        val currentDays = studyDayDao.getAllDays().first()
        if (currentDays.isEmpty()) {
            studyDayDao.insertDays(InitialData.studyDays)
        }
    }
}
