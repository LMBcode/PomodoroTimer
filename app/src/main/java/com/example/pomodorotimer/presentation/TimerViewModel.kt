package com.example.pomodorotimer.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodorotimer.data.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(val sharedPreferences: Preferences) : ViewModel() {



    fun saveTimer(timer : Int){
        sharedPreferences.saveTimer(timer)
    }


    fun saveShortBreak(shortBreak : Int){
            sharedPreferences.saveShortBreak(shortBreak)

    }

    fun saveLongBreak(longBreak : Int){
            sharedPreferences.saveLongBreak(longBreak)
    }

    fun getTimer() : Int{
        return sharedPreferences.loadTimer()
    }

    fun getTimerLong() : Int {
        return sharedPreferences.loadLongTimer()
    }

    fun getTimerShort() : Int{
        return sharedPreferences.loadShortTimer()
    }
}