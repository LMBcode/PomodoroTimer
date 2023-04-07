package com.example.pomodorotimer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodorotimer.ui.theme.Avenir_bold
import com.example.pomodorotimer.ui.theme.Avenir_regular
import com.example.pomodorotimer.ui.theme.darkPurple
import com.example.pomodorotimer.ui.theme.normalPurple


@Composable
fun TimerSettings(){

    val spacing = LocalSpacing.current

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Add a new Timer", style = TextStyle(fontFamily = Avenir_bold, color = Color.White, fontSize = 20.sp), modifier = Modifier.padding(top = spacing.spaceMedium))

        Spacer(modifier = Modifier.padding(vertical = spacing.spaceLarge) )

        RowTimer(text = "Focus Time", time = "20 min")

        RowTimer(text = "Short Break", time = "5 min")

        RowTimer(text = "Long Break", time = "15 min")

    }
}


@Composable
fun RowTimer(text : String , time : String){

    Card(modifier =
    Modifier
        .padding(8.dp)
        .height(55.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(50),
        elevation = 8.dp,
        backgroundColor = normalPurple
    )
    {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)) {
            Text(text = text, style = TextStyle(fontFamily = Avenir_regular, fontSize = 20.sp))
            
            Text(text = time,style = TextStyle(fontFamily = Avenir_regular,fontSize = 20.sp))
        }
    }

}