package com.example.data.db

import androidx.room.*
import com.example.data.model.StudyDay
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyDayDao {
    @Query("SELECT * FROM study_days ORDER BY dayNumber ASC")
    fun getAllDays(): Flow<List<StudyDay>>

    @Query("SELECT * FROM study_days WHERE dayNumber = :dayNumber LIMIT 1")
    suspend fun getDayByNumber(dayNumber: Int): StudyDay?

    @Query("SELECT * FROM study_days WHERE dayNumber = :dayNumber LIMIT 1")
    fun getDayByNumberFlow(dayNumber: Int): Flow<StudyDay?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDays(days: List<StudyDay>)

    @Update
    suspend fun updateDay(day: StudyDay)

    @Query("UPDATE study_days SET isCompleted = :completed WHERE dayNumber = :dayNumber")
    suspend fun updateCompletionStatus(dayNumber: Int, completed: Boolean)

    @Query("UPDATE study_days SET notes = :notes WHERE dayNumber = :dayNumber")
    suspend fun updateDayNotes(dayNumber: Int, notes: String)
}
