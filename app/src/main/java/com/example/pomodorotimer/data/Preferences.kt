package com.example.pomodorotimer.data

interface Preferences {

    fun userName(name: String)
    fun saveTimer(time: Int)
    fun saveShortBreak(time: Int)
    fun saveLongBreak(time: Int)


    fun loadTimer() : Int
    fun loadLongTimer() : Int
    fun loadShortTimer() : Int



    companion object {
        const val USER_KEY = "user"
        const val KEY_TIMER = "timer"
        const val KEY_LONG_BREAK = "longtimer"
        const val KEY_SHORT_BREAK = "shorttimer"


    }
}