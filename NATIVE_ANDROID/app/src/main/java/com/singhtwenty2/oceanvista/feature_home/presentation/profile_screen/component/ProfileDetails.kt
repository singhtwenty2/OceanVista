package com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen.ProfileState

@Composable
fun ProfileDetails(profile: ProfileState) {
    val detailItems = listOf(
        ProfileDetailItem(
            icon = Icons.Default.Email,
            text = profile.profile.email
        ),
        ProfileDetailItem(
            icon = Icons.Default.Phone,
            text = profile.profile.mobileNumber
        ),
        ProfileDetailItem(
            icon = Icons.Default.Person,
            text = "Notification Preference: ${profile.profile.notificationPrefrence}"
        )
    )

    detailItems.forEachIndexed { index, item ->
        ProfileDetailItemView(
            icon = item.icon,
            text = item.text
        )
        if (index < detailItems.size - 1) {
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}