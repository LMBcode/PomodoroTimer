package com.example.pomodorotimer.data

import android.content.SharedPreferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override fun userName(name: String) {
        sharedPref.edit()
            .putString(Preferences.USER_KEY, name)
            .apply()
    }

    override fun saveTimer(time: Int) {
        sharedPref.edit()
            .putInt(Preferences.KEY_TIMER, time)
            .apply()
    }

    override fun saveShortBreak(time: Int) {
        sharedPref.edit()
            .putInt(Preferences.KEY_SHORT_BREAK, time)
            .apply()
    }

    override fun saveLongBreak(time: Int) {
        sharedPref.edit()
            .putInt(Preferences.KEY_LONG_BREAK, time)
            .apply()
    }

    override fun loadTimer(): Int {
        return sharedPref.getInt(Preferences.KEY_TIMER,25)
    }

    override fun loadLongTimer(): Int {
        return sharedPref.getInt(Preferences.KEY_LONG_BREAK,15)

    }

    override fun loadShortTimer(): Int {
        return sharedPref.getInt(Preferences.KEY_SHORT_BREAK,5)

    }



}