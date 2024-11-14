package com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun TermsAndPolicySection(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onPolicyClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "I accept the Terms and Conditions",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.clickable { onCheckedChange(!isChecked) }
            )
            Text(
                text = "Privacy Policy",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.clickable(onClick = onPolicyClick)
            )
        }
    }
}