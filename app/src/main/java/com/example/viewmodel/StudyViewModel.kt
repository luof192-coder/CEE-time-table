package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.StudyDay
import com.example.data.repository.StudyRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StudyViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application, viewModelScope)
    private val repository = StudyRepository(database.studyDayDao())

    // All 60 days
    val allDays: StateFlow<List<StudyDay>> = repository.allDays
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _selectedDayNumber = MutableStateFlow(1)
    val selectedDayNumber: StateFlow<Int> = _selectedDayNumber.asStateFlow()

    // Global topic search query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Dynamic search filtering
    val filteredDays: StateFlow<List<StudyDay>> = combine(allDays, searchQuery) { days, query ->
        if (query.isBlank()) {
            days
        } else {
            days.filter { day ->
                day.physics.contains(query, ignoreCase = true) ||
                        day.chemistry.contains(query, ignoreCase = true) ||
                        day.zoology.contains(query, ignoreCase = true) ||
                        day.botany.contains(query, ignoreCase = true) ||
                        day.mat.contains(query, ignoreCase = true) ||
                        "Day ${day.dayNumber}".contains(query, ignoreCase = true) ||
                        day.phase.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Monitored single active day
    val activeDay: StateFlow<StudyDay?> = combine(allDays, _selectedDayNumber) { days, activeNum ->
        days.find { it.dayNumber == activeNum }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Completion percentage state
    val completionProgress: StateFlow<Float> = allDays.map { list ->
        if (list.isEmpty()) 0f
        else list.count { it.isCompleted }.toFloat() / list.size
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0f)

    val completedDaysCount: StateFlow<Int> = allDays.map { list ->
        list.count { it.isCompleted }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // -------------------------------------------------------------
    // Mock Exam Score Calculator states
    // -------------------------------------------------------------
    val examTotalQuestions = MutableStateFlow("200")
    val examCorrectAnswers = MutableStateFlow("140")
    val examIncorrectAnswers = MutableStateFlow("40")

    data class CalculatorResult(
        val total: Int,
        val correct: Int,
        val incorrect: Int,
        val unanswered: Int,
        val netScore: Double,
        val accuracyPercentage: Double,
        val isValid: Boolean,
        val errorMessage: String? = null
    )

    val calculatorResult: StateFlow<CalculatorResult> = combine(
        examTotalQuestions,
        examCorrectAnswers,
        examIncorrectAnswers
    ) { totalStr, correctStr, incorrectStr ->
        val total = totalStr.toIntOrNull() ?: 0
        val correct = correctStr.toIntOrNull() ?: 0
        val incorrect = incorrectStr.toIntOrNull() ?: 0

        if (total <= 0) {
            return@combine CalculatorResult(
                total = 0,
                correct = 0,
                incorrect = 0,
                unanswered = 0,
                netScore = 0.0,
                accuracyPercentage = 0.0,
                isValid = false,
                errorMessage = "Total practiced must be greater than 0."
            )
        }
        if (correct < 0 || incorrect < 0) {
            return@combine CalculatorResult(
                total = total,
                correct = 0,
                incorrect = 0,
                unanswered = 0,
                netScore = 0.0,
                accuracyPercentage = 0.0,
                isValid = false,
                errorMessage = "Input numbers cannot be negative."
            )
        }
        if (correct + incorrect > total) {
            return@combine CalculatorResult(
                total = total,
                correct = correct,
                incorrect = incorrect,
                unanswered = 0,
                netScore = 0.0,
                accuracyPercentage = 0.0,
                isValid = false,
                errorMessage = "Sum of Correct ($correct) & Incorrect ($incorrect) exceeds practice Total ($total)."
            )
        }

        val unanswered = total - (correct + incorrect)
        val netScore = (correct * 1.0) + (incorrect * -0.25)
        val accuracy = if (correct + incorrect > 0) {
            (correct.toDouble() / (correct + incorrect)) * 100.0
        } else {
            0.0
        }

        CalculatorResult(
            total = total,
            correct = correct,
            incorrect = incorrect,
            unanswered = unanswered,
            netScore = netScore,
            accuracyPercentage = accuracy,
            isValid = true,
            errorMessage = null
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CalculatorResult(200, 140, 40, 20, 130.0, 77.7, true))

    // -------------------------------------------------------------
    // Pomodoro Timer States
    // -------------------------------------------------------------
    private val _timerDurationSeconds = MutableStateFlow(50 * 60L) // 50m preset by default
    val timerDurationSeconds: StateFlow<Long> = _timerDurationSeconds.asStateFlow()

    private val _timerSecondsRemaining = MutableStateFlow(50 * 60L)
    val timerSecondsRemaining: StateFlow<Long> = _timerSecondsRemaining.asStateFlow()

    private val _timerIsRunning = MutableStateFlow(false)
    val timerIsRunning: StateFlow<Boolean> = _timerIsRunning.asStateFlow()

    private val _showTimerCompletionModal = MutableStateFlow(false)
    val showTimerCompletionModal: StateFlow<Boolean> = _showTimerCompletionModal.asStateFlow()

    private var timerJob: Job? = null

    init {
        viewModelScope.launch {
            repository.ensureDatabaseSeeded()
        }
    }

    fun selectDay(dayNumber: Int) {
        if (dayNumber in 1..60) {
            _selectedDayNumber.value = dayNumber
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleDayCompletion(dayNumber: Int, completed: Boolean) {
        viewModelScope.launch {
            repository.updateCompletion(dayNumber, completed)
        }
    }

    fun updateDayNotes(dayNumber: Int, notes: String) {
        viewModelScope.launch {
            repository.updateNotes(dayNumber, notes)
        }
    }

    fun setTimerPreset(minutes: Int) {
        stopTimer()
        val secs = minutes * 60L
        _timerDurationSeconds.value = secs
        _timerSecondsRemaining.value = secs
    }

    fun startTimer() {
        if (_timerIsRunning.value) return
        _timerIsRunning.value = true
        timerJob = viewModelScope.launch {
            while (_timerSecondsRemaining.value > 0) {
                delay(1000)
                _timerSecondsRemaining.value -= 1
            }
            _timerIsRunning.value = false
            _showTimerCompletionModal.value = true
        }
    }

    fun pauseTimer() {
        _timerIsRunning.value = false
        timerJob?.cancel()
    }

    fun stopTimer() {
        _timerIsRunning.value = false
        timerJob?.cancel()
        _timerSecondsRemaining.value = _timerDurationSeconds.value
    }

    fun dismissTimerCompletionModal() {
        _showTimerCompletionModal.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
