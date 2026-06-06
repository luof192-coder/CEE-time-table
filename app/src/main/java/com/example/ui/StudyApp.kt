package com.example.ui

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.StudyDay
import com.example.ui.theme.*
import com.example.viewmodel.StudyViewModel
import java.util.Locale

data class CalcDecisionState(
    val title: String,
    val message: String,
    val bgColor: Color,
    val accentColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyApp(viewModel: StudyViewModel) {
    val allDays by viewModel.allDays.collectAsState()
    val filteredDays by viewModel.filteredDays.collectAsState()
    val activeDay by viewModel.activeDay.collectAsState()
    val selectedDayNumber by viewModel.selectedDayNumber.collectAsState()
    val completionProgress by viewModel.completionProgress.collectAsState()
    val completedDaysCount by viewModel.completedDaysCount.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    // Tab state
    var currentTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("🗓️ Day Planner", "📊 Score Calculator", "⏱️ Focus Timer")

    // Overlay full dialog for timer
    val showTimerCompletionModal by viewModel.showTimerCompletionModal.collectAsState()

    Scaffold(
        topBar = {
            // HIGH-END DARK GRADIENT HEADER containing dynamic percentage progress bar
            HeaderGradientBlock(
                completionProgress = completionProgress,
                completedCount = completedDaysCount,
                totalCount = allDays.size
            )
        },
        bottomBar = {
            Column(modifier = Modifier.navigationBarsPadding()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFE2E8F0)) // border-slate-200
                )
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 0.dp
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        val isSelected = currentTab == index
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { currentTab = index },
                            icon = {
                                val icon = when (index) {
                                    0 -> Icons.Default.DateRange
                                    1 -> Icons.Default.Star
                                    else -> Icons.Default.CheckCircle
                                }
                                Icon(
                                    imageVector = icon,
                                    contentDescription = title,
                                    tint = if (isSelected) Indigo600 else Slate700
                                )
                            },
                            label = {
                                Text(
                                    text = title.substring(3), // trim emoji for tab label
                                    color = if (isSelected) Indigo600 else Slate700,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 11.sp
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color(0xFFEEF2F6)
                            )
                        )
                    }
                }
            }
        },
        containerColor = Slate50
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                0 -> DayPlannerTabContent(
                    viewModel = viewModel,
                    allDays = allDays,
                    filteredDays = filteredDays,
                    activeDay = activeDay,
                    searchQuery = searchQuery,
                    selectedDayNumber = selectedDayNumber
                )
                1 -> ScoreCalculatorTabContent(
                    viewModel = viewModel
                )
                2 -> FocusTimerTabContent(
                    viewModel = viewModel
                )
            }

            // Customized Timer Overlay Modal Dialog
            if (showTimerCompletionModal) {
                TimerCompletionModal(onDismiss = { viewModel.dismissTimerCompletionModal() })
            }
        }
    }
}

@Composable
fun HeaderGradientBlock(
    completionProgress: Float,
    completedCount: Int,
    totalCount: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Indigo950, Slate950, Indigo900)
                )
            )
            .statusBarsPadding()
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "CEE NEPAL 2026",
                        color = Color(0xFFA5B4FC), // Indigo-300 tone
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Study Mastery",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Day $completedCount",
                        color = Color(0xFF34D399), // emerald-400
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                    )
                    Text(
                        text = "OF $totalCount DAYS",
                        color = Color(0xFF94A3B8), // slate-400
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Premium Slimming Progress bar: h-1.5 is 6.dp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(Color.White.copy(alpha = 0.1f), CircleShape)
            ) {
                val coercedProgress = completionProgress.coerceIn(0f, 1f)
                if (coercedProgress > 0f) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(coercedProgress)
                            .background(
                                color = Color(0xFF10B981), // bg-emerald-500
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun DayPlannerTabContent(
    viewModel: StudyViewModel,
    allDays: List<StudyDay>,
    filteredDays: List<StudyDay>,
    activeDay: StudyDay?,
    searchQuery: String,
    selectedDayNumber: Int
) {
    var showFullGrid by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        // TOP SEARCH BAR & GRID TOGGLE ROW
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                placeholder = { Text("Search topics (e.g. Krebs, Bohr, Vector)", fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = Slate700) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.setSearchQuery("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear", tint = Slate700)
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .testTag("topic_search_input"),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Indigo600,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                singleLine = true
            )

            Button(
                onClick = { showFullGrid = !showFullGrid },
                modifier = Modifier
                    .height(52.dp)
                    .testTag("view_toggle_button"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (showFullGrid) Indigo900 else Color.White,
                    contentColor = if (showFullGrid) Color.White else Indigo600
                ),
                border = if (!showFullGrid) BorderStroke(1.dp, Slate200) else null,
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Icon(
                    imageVector = if (showFullGrid) Icons.Default.List else Icons.Default.Menu,
                    contentDescription = "Grid Map"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = if (showFullGrid) "Hide Map" else "60 Days Map", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        // GRID SELECTOR MAP OF ALL 60 DAYS (EXPANDABLE)
        AnimatedVisibility(
            visible = showFullGrid,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "CEE Nepal 60-Day Progress Map",
                        fontWeight = FontWeight.Bold,
                        color = Slate900,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // 6x10 scrollable grid
                    Box(modifier = Modifier.height(180.dp)) {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 48.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            items(allDays) { day ->
                                val isSelected = day.dayNumber == selectedDayNumber
                                Box(
                                    modifier = Modifier
                                        .size(46.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(
                                            when {
                                                isSelected -> Indigo600
                                                day.isCompleted -> Color(0xFFD1FAE5)
                                                else -> Slate100
                                            }
                                        )
                                        .border(
                                            width = if (isSelected) 2.dp else 1.dp,
                                            color = when {
                                                isSelected -> Indigo900
                                                day.isCompleted -> Emerald600
                                                else -> Slate200
                                            },
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .clickable {
                                            viewModel.selectDay(day.dayNumber)
                                        }
                                        .testTag("grid_day_${day.dayNumber}"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = day.dayNumber.toString(),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Black,
                                            color = when {
                                                isSelected -> Color.White
                                                day.isCompleted -> Color(0xFF064E3B)
                                                else -> Slate700
                                            }
                                        )
                                        if (day.isCompleted) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = "Done",
                                                tint = Emerald600,
                                                modifier = Modifier.size(10.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // HORIZONTAL DAY Badges list - shown if grid is closed
        AnimatedVisibility(visible = !showFullGrid) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredDays) { day ->
                    val isSelected = day.dayNumber == selectedDayNumber
                    val badgeBg = when {
                        isSelected -> Indigo600
                        day.isCompleted -> Color(0xFFD1FAE5)
                        else -> Color.White
                    }
                    val badgeBorderColor = when {
                        isSelected -> Indigo900
                        day.isCompleted -> Emerald600
                        else -> Slate200
                    }
                    val badgeTextColor = when {
                        isSelected -> Color.White
                        day.isCompleted -> Color(0xFF064E3B)
                        else -> Slate900
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(badgeBg)
                            .border(1.dp, badgeBorderColor, RoundedCornerShape(12.dp))
                            .clickable { viewModel.selectDay(day.dayNumber) }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                            .testTag("badge_day_${day.dayNumber}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Day ${day.dayNumber}",
                                color = badgeTextColor,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 12.sp
                            )
                            if (day.isCompleted) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Completed",
                                    tint = Emerald600,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // SEARCH ZERO STATE HINT
        if (filteredDays.isEmpty() && searchQuery.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "No results",
                        tint = Slate700,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "No syllabus topics match \"$searchQuery\"",
                        color = Slate700,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Try searching for simpler medical topics like: 'cell', 'Bohr', 'Krebs', or 'fluid'.",
                        fontSize = 12.sp,
                        color = Slate700,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            // MAIN SYLLABUS CARD DETAILS FOR SELECTED DAY
            activeDay?.let { day ->
                ActiveDayDetailsBlock(
                    day = day,
                    viewModel = viewModel,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ActiveDayDetailsBlock(
    day: StudyDay,
    viewModel: StudyViewModel,
    modifier: Modifier = Modifier
) {
    var notesText by remember(day.dayNumber) { mutableStateOf(day.notes) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        // DAY HEADER CARD - Styled cleanly in Minimal White with Slate-200 Border
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(Indigo600, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "CURRENT PLAN",
                            color = Slate700,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Day ${day.dayNumber} : Syllabus Focus",
                        color = Slate900,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    // Styled dynamic emerald badge matching Objectives Card in design
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFD1FAE5)) // light emerald bg
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = day.phase,
                            color = Color(0xFF065F46), // solid emerald text
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Checkbox switch
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Checkbox(
                        checked = day.isCompleted,
                        onCheckedChange = { viewModel.toggleDayCompletion(day.dayNumber, it) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Emerald600,
                            uncheckedColor = Slate700,
                            checkmarkColor = Color.White
                        ),
                        modifier = Modifier.testTag("day_complete_checkbox")
                    )
                    Text(
                        text = if (day.isCompleted) "Completed" else "Mark Done",
                        color = if (day.isCompleted) Emerald600 else Slate700,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // CORE SYLLABUS TOPICS BY SUBJECTS
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Indigo600, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Daily Syllabus Targets (MEC CEE Weighted)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )
        }

        val subjectTopics = listOf(
            Triple("⚛️ Physics (50 Qs)", day.physics, Indigo400),
            Triple("🧪 Chemistry (50 Qs)", day.chemistry, Indigo600),
            Triple("🦁 Zoology (40 Qs)", day.zoology, Emerald600),
            Triple("🌿 Botany (40 Qs)", day.botany, Emerald600),
            Triple("🧩 Mental Agility (20 Qs)", day.mat, Slate700)
        )

        subjectTopics.forEach { (subTitle, topic, primaryColor) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .offset(y = 4.dp)
                            .background(primaryColor, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = subTitle,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = primaryColor
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = topic,
                            color = Slate800,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // CLASSROOM EXCLUSION TIMELINE
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(InfoBlue, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "MEC Institute Classroom Exclusion Loop",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Self-study triggers start strictly outside the 6:00 AM to 11:00 AM class blocks:",
                    fontSize = 11.sp,
                    color = Slate700,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Time block rows
                TimeBlockRow(time = "06:00 AM - 11:00 AM", task = "🏫 Classroom session at coaching institute (EXCLUDED from independent study list)", isExcluded = true)
                TimeBlockRow(time = "11:00 AM - 12:00 PM", task = "🍛 Lunch & travel decompression transition", isExcluded = false)
                TimeBlockRow(time = "12:00 PM - 02:00 PM", task = "⚛️ Physics study block on: ${day.physics.take(40)}...", isExcluded = false)
                TimeBlockRow(time = "02:00 PM - 04:00 PM", task = "🧪 Chemistry study block on: ${day.chemistry.take(40)}...", isExcluded = false)
                TimeBlockRow(time = "04:00 PM - 04:30 PM", task = "☕ Mindful relaxation and breathing break", isExcluded = false)
                TimeBlockRow(time = "04:30 PM - 06:00 PM", task = "🦁 Zoology core study on: ${day.zoology.take(40)}...", isExcluded = false)
                TimeBlockRow(time = "06:00 PM - 07:30 PM", task = "🌿 Botany core study on: ${day.botany.take(40)}...", isExcluded = false)
                TimeBlockRow(time = "07:30 PM - 08:30 PM", task = "🧩 MAT problem-solving practice on: ${day.mat.take(40)}...", isExcluded = false)
                TimeBlockRow(time = "08:30 PM - 09:30 PM", task = "🍲 Dinner & family interaction", isExcluded = false)
                TimeBlockRow(time = "09:30 PM - 11:00 PM", task = "📝 MCQ practice tracker: Attempt matching mock items & count final score", isExcluded = false)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // PERSONAL STUDY LOGS / NOTES (Room Persisted)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Indigo600, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Personal Practice Study Notes (Saved)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = notesText,
                    onValueChange = { notesText = it },
                    placeholder = { Text("Log your weak topics, formula cards or target goals for Day ${day.dayNumber}...", fontSize = 12.sp, color = Color(0xFF94A3B8)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .testTag("day_notes_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Indigo600,
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        focusedContainerColor = Color(0xFFF8FAFC),
                        unfocusedContainerColor = Color(0xFFF8FAFC)
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        viewModel.updateDayNotes(day.dayNumber, notesText)
                        focusManager.clearFocus()
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Indigo600
                    ),
                    modifier = Modifier
                        .align(Alignment.End)
                        .testTag("save_notes_button")
                ) {
                    Text("Save Study Logs", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun TimeBlockRow(time: String, task: String, isExcluded: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = time,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = if (isExcluded) Slate700 else Indigo600,
            modifier = Modifier.width(115.dp)
        )
        Text(
            text = task,
            fontSize = 11.sp,
            color = if (isExcluded) Color.Gray else Slate800,
            fontWeight = if (isExcluded) FontWeight.Normal else FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ScoreCalculatorTabContent(
    viewModel: StudyViewModel
) {
    val examTotalQuestions by viewModel.examTotalQuestions.collectAsState()
    val examCorrectAnswers by viewModel.examCorrectAnswers.collectAsState()
    val examIncorrectAnswers by viewModel.examIncorrectAnswers.collectAsState()
    val calculatorResult by viewModel.calculatorResult.collectAsState()

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // SUMMARY INTRODUCTION
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Indigo600, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Real-Time Mock Exam Score Scoreboard",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )
        }
        Text(
            text = "Calculates using 2026 Nepal MEC marking rubric: +1 for Correct, -0.25 for Incorrect, 0 for Unanswered. Threshold is 50th percentile ranking pass.",
            fontSize = 11.sp,
            color = Slate700,
            lineHeight = 15.sp,
            modifier = Modifier.padding(bottom = 14.dp)
        )

        // INPUT CARDS
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Practice Quantities",
                    fontWeight = FontWeight.Bold,
                    color = Indigo600,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // Input fields inside grid-like format
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Total Practice", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate700)
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = examTotalQuestions,
                            onValueChange = { viewModel.examTotalQuestions.value = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            placeholder = { Text("200") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("total_questions_input"),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Indigo600,
                                unfocusedBorderColor = Color(0xFFE2E8F0),
                                focusedContainerColor = Color(0xFFF8FAFC),
                                unfocusedContainerColor = Color(0xFFF8FAFC)
                            ),
                            singleLine = true
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("✓ Correct", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Emerald600)
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = examCorrectAnswers,
                            onValueChange = { viewModel.examCorrectAnswers.value = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            placeholder = { Text("140") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("correct_answers_input"),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Indigo600,
                                unfocusedBorderColor = Color(0xFFE2E8F0),
                                focusedContainerColor = Color(0xFFF8FAFC),
                                unfocusedContainerColor = Color(0xFFF8FAFC)
                            ),
                            singleLine = true
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("✗ Incorrect", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = WarningCoral)
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = examIncorrectAnswers,
                            onValueChange = { viewModel.examIncorrectAnswers.value = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            placeholder = { Text("40") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("incorrect_answers_input"),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Indigo600,
                                unfocusedBorderColor = Color(0xFFE2E8F0),
                                focusedContainerColor = Color(0xFFF8FAFC),
                                unfocusedContainerColor = Color(0xFFF8FAFC)
                            ),
                            singleLine = true
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // REAL-TIME VALIDATION WARNING CARD
        if (!calculatorResult.isValid && calculatorResult.errorMessage != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = WarningBg),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, WarningCoral.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Warning, contentDescription = "Error", tint = WarningCoral)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = calculatorResult.errorMessage!!,
                        color = Color(0xFF9F1239),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            // OUTPUT DISPLAY BOARD
            Column(modifier = Modifier.fillMaxWidth()) {
                // Main stats row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Net Score Card
                    Card(
                        modifier = Modifier
                            .weight(1.2f)
                            .height(105.dp),
                        colors = CardDefaults.cardColors(containerColor = Indigo950),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("NET SCORE (MEC)", color = Color(0xFFA5B4FC), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            Text(
                                text = String.format(Locale.US, "%.2f", calculatorResult.netScore),
                                color = Color.White,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text("out of ${calculatorResult.total} questions", color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp)
                        }
                    }

                    // Accuracy rate Card
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(105.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("ACCURACY RATE", color = Slate700, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            Text(
                                text = String.format(Locale.US, "%.1f%%", calculatorResult.accuracyPercentage),
                                color = if (calculatorResult.accuracyPercentage >= 70) Emerald600 else WarningCoral,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text("Active speed ratio", color = Slate700.copy(alpha = 0.6f), fontSize = 10.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Breakdown stats card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        VerticalMetric(label = "Correct (+1)", value = calculatorResult.correct.toString(), color = Emerald600)
                        Box(modifier = Modifier.height(36.dp).width(1.dp).background(Color(0xFFE2E8F0)))
                        VerticalMetric(label = "Wrong (-0.25)", value = calculatorResult.incorrect.toString(), color = WarningCoral)
                        Box(modifier = Modifier.height(36.dp).width(1.dp).background(Color(0xFFE2E8F0)))
                        VerticalMetric(label = "Unanswered (0)", value = calculatorResult.unanswered.toString(), color = Slate700)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // TAILORED DECISION QUALITATIVE ALERT CARD
                val accuracy = calculatorResult.accuracyPercentage
                val netScore = calculatorResult.netScore
                val total = calculatorResult.total

                val decisionCardState = when {
                    netScore / total >= 0.70 -> CalcDecisionState(
                        title = "🔥 Outstanding Merit Tier",
                        message = "With a score ratio of ${(netScore/total*100).toInt()}%, you are highly aligned to secure a government seat in top institutions (IOM Maharaging, BPKIHS Dharan). Maintain high speed and reduce random ticks!",
                        bgColor = Color(0xFFE0F2FE),
                        accentColor = InfoBlue
                    )
                    netScore / total >= 0.50 -> CalcDecisionState(
                        title = "🌟 High Merit Rank Bracket",
                        message = "You stand ahead of the average 50th percentile rank line. Build accuracy to 80% with consistent mock question solving to lock down premium scholarship brackets.",
                        bgColor = Color(0xFFD1FAE5),
                        accentColor = Emerald600
                    )
                    netScore / total >= 0.35 -> CalcDecisionState(
                        title = "📈 Passing Zone Bound",
                        message = "Current mock score is approaching qualification threshold. Increase focus on high-yield cells chapters & mechanics equations in your study log.",
                        bgColor = Color(0xFFFEF3C7),
                        accentColor = Color(0xFFD97706)
                    )
                    else -> CalcDecisionState(
                        title = "⚠️ Revision Required Zone",
                        message = "Score falls beneath 50% merit lines. Pivot to foundational 60 days targets first: review atomic trends, tissues, and verbal-direction guides inside week plans.",
                        bgColor = Color(0xFFFEE2E2),
                        accentColor = WarningCoral
                    )
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = decisionCardState.bgColor),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, decisionCardState.accentColor.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(decisionCardState.accentColor, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = decisionCardState.title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = decisionCardState.accentColor
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = decisionCardState.message,
                            color = Slate900,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // RE-SET HINT CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(WarningCoral, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Quick marking tip:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate700
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Avoid wild guessing in CEE Nepal! Every incorrect answer costs you 0.25 marks, which means 4 wrong answers wipe out 1 correct mark. If unsure, leaving it unanswered (0 marks) is superior.",
                    fontSize = 10.sp,
                    color = Slate700,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
fun VerticalMetric(label: String, value: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(text = label, color = Slate700, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, color = color, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun FocusTimerTabContent(
    viewModel: StudyViewModel
) {
    val durationSeconds by viewModel.timerDurationSeconds.collectAsState()
    val remainingSeconds by viewModel.timerSecondsRemaining.collectAsState()
    val isRunning by viewModel.timerIsRunning.collectAsState()

    // Formatted MM:SS
    val minutes = remainingSeconds / 60
    val seconds = remainingSeconds % 60
    val timeFormatted = String.format(Locale.US, "%02d:%02d", minutes, seconds)

    // Progress percentage
    val progress = if (durationSeconds > 0) {
        remainingSeconds.toFloat() / durationSeconds
    } else 1f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Indigo600, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Integrated Pomodoro Focus Engine",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )
        }
        Text(
            text = "Train with intensive cycles to map MEC syllabus sections. Set presets, lock into self-study blocks, and build deep focus blocks.",
            fontSize = 11.sp,
            color = Slate700,
            modifier = Modifier.align(Alignment.Start)
        )

        // CHOOSE PRESET BUTTONS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.setTimerPreset(50) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (durationSeconds == 50 * 60L) Indigo600 else Color(0xFFF1F5F9),
                    contentColor = if (durationSeconds == 50 * 60L) Color.White else Color(0xFF475569)
                ),
                border = BorderStroke(1.dp, if (durationSeconds == 50 * 60L) Color.Transparent else Color(0xFFE2E8F0)),
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp)
                    .testTag("preset_50_button"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("🧠 50 Min Focus", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { viewModel.setTimerPreset(25) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (durationSeconds == 25 * 60L) Indigo600 else Color(0xFFF1F5F9),
                    contentColor = if (durationSeconds == 25 * 60L) Color.White else Color(0xFF475569)
                ),
                border = BorderStroke(1.dp, if (durationSeconds == 25 * 60L) Color.Transparent else Color(0xFFE2E8F0)),
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp)
                    .testTag("preset_25_button"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("☘️ 25 Min Recall", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // PREMIUM CIRCULAR COUNTDOWN DIAL CARD
        Card(
            modifier = Modifier
                .size(240.dp)
                .testTag("timer_dial_card"),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            border = BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Circular Indicator Ring
                CircularProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxSize(0.9f),
                    color = if (isRunning) Emerald600 else Indigo600,
                    strokeWidth = 8.dp,
                    trackColor = Color(0xFFF1F5F9)
                )

                // Large Countdown text
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = timeFormatted,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = if (isRunning) "ACTIVE FOCUS CYCLE" else "PAUSED",
                        color = if (isRunning) Emerald600 else Slate700,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // OPERATIONAL ACTION CONTROLS BUTTONS ROW
        Row(
            modifier = Modifier.fillMaxWidth(0.85f),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Play/Pause button
            Button(
                onClick = {
                    if (isRunning) {
                        viewModel.pauseTimer()
                    } else {
                        viewModel.startTimer()
                    }
                },
                modifier = Modifier
                    .weight(1.5f)
                    .height(52.dp)
                    .testTag("timer_toggle_button"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRunning) WarningCoral else Emerald600
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isRunning) Icons.Default.Close else Icons.Default.PlayArrow,
                        contentDescription = "Control"
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isRunning) "Pause" else "Start Session",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            // Reset button
            Button(
                onClick = { viewModel.stopTimer() },
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .testTag("timer_reset_button"),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Slate900
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Refresh, contentDescription = "Reset")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // TRIVIA STUDY FACT CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFFEAB308), CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Why 50 Minutes Pomodoro?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Slate900
                    )
                    Text(
                        text = "The CEE entrance exam is a 3-hour marathon of 200 high-speed questions. Focus training in 50-minute blocks replicates sub-sections of the actual exam stamina perfectly.",
                        fontSize = 10.sp,
                        color = Slate700,
                        lineHeight = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TimerCompletionModal(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(2.dp, Color(0xFF10B981)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("timer_completion_dialog")
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFD1FAE5), CircleShape)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Award",
                        tint = Emerald600,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "🎉 Session Achieved!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Slate900,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "A complete focus block is achieved! Deep visual tracking shows high retention. Take a nice 10-minute breaks, relax your eyes, and mark the active day in your MEC Day-by-Day Syllabus planner.",
                    fontSize = 11.sp,
                    color = Slate700,
                    textAlign = TextAlign.Center,
                    lineHeight = 15.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Emerald600
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("timer_dialog_okay_button")
                ) {
                    Text("Decompress & Resume", fontWeight = FontWeight.ExtraBold)
                }
            }
        }
    }
}

@Composable
fun Slate250Color() = Color(0xFFCBD5E1) // outline color fallback
