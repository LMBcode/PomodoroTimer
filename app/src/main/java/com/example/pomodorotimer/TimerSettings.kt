package com.example.pomodorotimer

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
import com.example.pomodorotimer.ui.theme.Avenir_bold
import com.example.pomodorotimer.ui.theme.Avenir_regular
import com.example.pomodorotimer.ui.theme.lightPurple
import com.example.pomodorotimer.ui.theme.normalPurple


@Composable
fun TimerSettings(){

    val spacing = LocalSpacing.current

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Add a new Timer", style = TextStyle(fontFamily = Avenir_bold, color = Color.White, fontSize = 20.sp), modifier = Modifier.padding(top = spacing.spaceMedium))

        Spacer(modifier = Modifier.padding(vertical = spacing.spaceLarge) )

        RowTimer(text = "Focus Time", initialTime = "20 min")

        RowTimer(text = "Short Break", initialTime = "5 min")

        RowTimer(text = "Long Break", initialTime = "15 min")

    }
}


@Composable
fun RowTimer(text : String, initialTime : String){


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