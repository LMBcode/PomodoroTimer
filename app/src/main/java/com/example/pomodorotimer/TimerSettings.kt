package com.example.pomodorotimer

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.pomodorotimer.data.DefaultPreferences
import com.example.pomodorotimer.data.Preferences
import com.example.pomodorotimer.presentation.CountDownViewModel
import com.example.pomodorotimer.presentation.TimerViewModel
import com.example.pomodorotimer.ui.theme.Avenir_bold
import com.example.pomodorotimer.ui.theme.Avenir_regular
import com.example.pomodorotimer.ui.theme.lightPurple
import com.example.pomodorotimer.ui.theme.normalPurple


@Composable
fun TimerSettings(viewModel: TimerViewModel = hiltViewModel(), countDownViewModel: CountDownViewModel = viewModel(), onClick : () -> Unit
) {
    val spacing = LocalSpacing.current
    val navController = rememberNavController()

    var focusTime by remember { mutableStateOf(25) }
    var shortBreak by remember { mutableStateOf(5) }
    var longBreak by remember { mutableStateOf(15) }
    val defaultTimer = viewModel.getTimer()  * 60 // Timer is in minutes , basically if timerViewModel.getTimer is equal to 25 , we should convert 25 minutes into seconds

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Add a new Timer",
            style = TextStyle(fontFamily = Avenir_bold, color = Color.White, fontSize = 20.sp),
            modifier = Modifier.padding(top = spacing.spaceMedium)
        )

        Spacer(modifier = Modifier.padding(vertical = spacing.spaceLarge))

        RowTimer(text = "Focus Time", initialTime = "${viewModel.getTimer()} min"){ selectedTime ->
            focusTime = selectedTime
        }

        RowTimer(text = "Short Break", initialTime = "${viewModel.getTimerShort()} min"){ selectedTime ->
            shortBreak = selectedTime
        }

        RowTimer(text = "Long Break", initialTime = "${viewModel.getTimerLong()} min"){ selectedTime ->
            longBreak = selectedTime
        }

        Button(onClick = {
            onClick()
            viewModel.saveTimer(focusTime)
            viewModel.saveShortBreak(shortBreak)
            viewModel.saveLongBreak(longBreak)
            countDownViewModel.resetCountdown(defaultTimer)

            Log.d("VALUES" , "Saved Values - Focus Time: ${viewModel.getTimer()}, Short Break: ${viewModel.getTimerShort()}, Long Break: ${viewModel.getTimerLong()}")

        }) {
            Text(text = "Save")
        }
    }
}



@Composable
fun RowTimer(text : String, initialTime : String,onTimeSelected: (Int) -> Unit){


    var expanded by remember {
        mutableStateOf(false)
    }
    var time by remember { mutableStateOf(initialTime) }

    var listItems = emptyList<Int>()
    when(text){
         "Focus Time" -> {listItems = listOf(25, 30, 45, 60) }
         "Short Break" -> {listItems = listOf(5, 10, 15, 30) }
         "Long Break" -> {listItems = listOf(25, 30, 45, 60) }
    }

    Card(modifier =
    Modifier
        .padding(8.dp)
        .height(55.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(50),
        elevation = 8.dp,
        backgroundColor = lightPurple,

    )
    {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)) {
            Text(text = text, style = TextStyle(fontFamily = Avenir_regular, fontSize = 20.sp))

            Text(
                text = time,
                style = TextStyle(fontFamily = Avenir_regular, fontSize = 20.sp),
            )

            Box() {
                Icon(imageVector =
                if (expanded) {
                    Icons.Default.ArrowDropUp
                } else {
                    Icons.Default.ArrowDropDown
                },
                    contentDescription = "DropDown",
                    modifier = Modifier.clickable { expanded = true }
                )

                MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(8.dp))) {
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .background(
                                lightPurple
                            )
                            .fillMaxWidth()
                    ) {
                        listItems.forEach { item ->
                            DropdownMenuItem(onClick = {
                                onTimeSelected(item)

                                // Update the selected value and close the dropdown
                                time = "$item min"
                                expanded = false
                            }) {
                                Text(
                                    text = "$item min",
                                    style = TextStyle(fontFamily = Avenir_regular, fontSize = 20.sp)
                                )
                            }
                        }
                    }
                }
            }

        }
    }

}