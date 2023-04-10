package com.example.pomodorotimer.presentation

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountDownViewModel : ViewModel() {
    private var countDownTimer: CountDownTimer? = null

    private val _countDownState = MutableStateFlow(0)
    val countDownState: MutableStateFlow<Int> = _countDownState

    fun startCountdown(timeInSeconds: Int) {
        if (countDownTimer != null) {
            countDownTimer?.cancel()
        }

        countDownTimer = object : CountDownTimer(timeInSeconds * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                _countDownState.value = (millisUntilFinished / 1000).toInt()
                Log.d("CountDownViewModel", "onTick: ${_countDownState.value}")

            }

            override fun onFinish() {
                _countDownState.value = 0
            }
        }
        countDownTimer?.start()
    }

    fun pauseCountdown() {
        countDownTimer?.cancel()
    }

    fun resetCountdown(timeInSeconds: Int) {
        countDownTimer?.cancel()
        _countDownState.value = timeInSeconds

    }

    override fun onCleared() {
        countDownTimer?.cancel()
        super.onCleared()
    }
}
