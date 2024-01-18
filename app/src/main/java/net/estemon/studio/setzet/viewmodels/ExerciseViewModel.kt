package net.estemon.studio.setzet.viewmodels

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import net.estemon.studio.setzet.models.standardExercises
import net.estemon.studio.setzet.ui.screens.common.stdExerciseTimeLimit

class ExerciseViewModel : ViewModel() {
    var isTimeRunning = mutableStateOf(false)
    var timeLeft = mutableIntStateOf(stdExerciseTimeLimit)
    var isReviewPhase = mutableStateOf(false)
    var dismissReviewDialog = mutableStateOf(false)

    var isReviewDone = mutableStateOf(false)
}