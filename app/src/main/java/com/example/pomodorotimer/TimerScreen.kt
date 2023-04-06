package com.example.pomodorotimer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pomodorotimer.presentation.CountDownViewModel
import com.example.pomodorotimer.presentation.Time.secondsToMMSS
import com.example.pomodorotimer.ui.theme.*
import java.lang.Math.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
     countDownViewModel: CountDownViewModel = viewModel()
){
    val spacing = LocalSpacing.current
    val countDownViewModelState = countDownViewModel.countDownState.collectAsState()


    val countDownPercentage = countDownViewModelState.value.toFloat() / 60f
    var isPlaying by remember{ mutableStateOf(false)}
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = spacing.spaceExtraLarge),
            text =  "Pomodoro Timer",
            style = TextStyle(fontFamily = Avenir_bold, fontSize = 20.sp, color = Color.White)
        )

        Spacer(modifier = Modifier.padding(spacing.spaceMedium))


            Box(modifier = modifier.align(Alignment.CenterHorizontally)) {
                CircularProgressBar(percentage = countDownPercentage, number = countDownViewModelState.value)
            }
        
        Spacer(modifier = Modifier.padding(spacing.spaceMedium))



        IconButton(
            onClick = {
                isPlaying = !isPlaying
                if (isPlaying) {
                    countDownViewModel.startCountdown()
                } else {
                    countDownViewModel.pauseCountdown()
                }

                      },
            modifier = Modifier
                .background(normalPurple, shape = CircleShape)
                .padding(4.dp)
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                contentDescription = "Start Timer",
                tint = Color.White
            )
        }


    }
}

@Composable
fun CircularProgressBar(
    percentage : Float,
    number : Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = normalPurple,
    strokeWidth : Dp = 4.dp,
    animDuration : Int = 60000,
    animDelay : Int = 0,
){
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val curPercentage = remember { mutableStateOf(percentage) }


    LaunchedEffect(key1 = true){
        animationPlayed = true
    }

    val indicatorOffset = with(LocalDensity.current) {
        (radius - strokeWidth / 2).toPx()
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 4f)
    ){
        Canvas(modifier = Modifier.size(radius * 4f)){
            drawArc(
                color = color,
                -90f,
                sweepAngle = 360 * percentage,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(),cap = StrokeCap.Round)
            )
            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (360f * percentage - 90f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = normalPurple,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )


        }

        Text(
            text = secondsToMMSS((curPercentage.value * number).toInt()),
            color = Color.White,
            fontSize = fontSize,
            style = TextStyle(fontFamily = Avenir_regular)

        )
    }

}

