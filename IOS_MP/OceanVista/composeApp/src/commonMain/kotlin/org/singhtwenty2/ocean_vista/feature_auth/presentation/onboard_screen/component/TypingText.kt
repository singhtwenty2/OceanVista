package org.singhtwenty2.ocean_vista.feature_auth.presentation.onboard_screen.component

import androidx.compose.foundation.text.BasicText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TypingTextComposable(
    modifier: Modifier = Modifier,
    texts: List<String>,
    textStyle: TextStyle = TextStyle(
        fontSize = 64.sp,
        color = MaterialTheme.colors.primary
    ),
    typingDelay: Long = 50L,
    displayDelay: Long = 3000L
) {
    var textIndex by remember { mutableStateOf(0) }
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(textIndex) {
        displayedText = ""
        texts[textIndex].forEach { char ->
            displayedText += char
            delay(typingDelay)
        }
        delay(displayDelay)
        textIndex = (textIndex + 1) % texts.size
    }

    BasicText(
        text = displayedText,
        style = textStyle,
        modifier = modifier
    )
}
