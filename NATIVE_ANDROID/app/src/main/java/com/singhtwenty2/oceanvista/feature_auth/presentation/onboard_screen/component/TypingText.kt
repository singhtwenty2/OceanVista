package com.singhtwenty2.oceanvista.feature_auth.presentation.onboard_screen.component

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TypingTextComposable(
    modifier: Modifier = Modifier,
    texts: List<String>,
    textStyle: TextStyle = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    ),
    typingDelay: Long = 110L,
    displayDelay: Long = 2000L,
    onCharacterTyped: () -> Unit,
    onTextCompleted: () -> Unit
) {
    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    var textIndex by remember { mutableIntStateOf(0) }
    var displayedText by remember { mutableStateOf("") }
    var isTypingForward by remember { mutableStateOf(true) }

    LaunchedEffect(textIndex, isTypingForward) {
        displayedText = if (isTypingForward) "" else texts[textIndex]

        val text = texts[textIndex]

        if (isTypingForward) {
            text.forEach { char ->
                displayedText += char
                onCharacterTyped()
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(VibrationEffect.createOneShot(10, VibrationEffect.DEFAULT_AMPLITUDE))
                }
                delay(typingDelay)
            }
            delay(displayDelay)
            isTypingForward = false
        } else {
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 10, 20), 0))
            }
            text.reversed().forEach { _ ->
                displayedText = displayedText.dropLast(1)
                delay(typingDelay - 60L)
            }
            vibrator.cancel()
            delay(typingDelay)
            isTypingForward = true
            textIndex = (textIndex + 1) % texts.size
            onTextCompleted()
        }
    }

    BasicText(
        text = displayedText,
        style = textStyle,
        modifier = modifier.alpha(1f)
    )
}