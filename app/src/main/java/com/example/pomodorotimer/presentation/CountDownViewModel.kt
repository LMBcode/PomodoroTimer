package com.example.pomodorotimer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountDownViewModel : ViewModel() {

     private var _countDownState = MutableStateFlow(60)
    val countDownState = _countDownState.asStateFlow()

    private var countdownJob: Job? = null


    fun updateCountdownValue(value: Int) {
        _countDownState.value = value
    }

    fun startCountdown() {
        val durationInSeconds = _countDownState.value
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            for (i in durationInSeconds - 1 downTo 0) {
                delay(1000)
                _countDownState.value = i
            }
        }
    }

    fun pauseCountdown(){
        countdownJob?.cancel()
    }


}