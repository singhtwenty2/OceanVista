package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.ConditionColor

fun mapConditionColorToDescription(beach: BeachColorCode): String {
    return when (beach.conditionColor) {
        ConditionColor.ORANGE -> "excellent"
        ConditionColor.YELLOW -> "moderate"
        ConditionColor.RED -> "poor"
        ConditionColor.GRAY -> "else"
        else -> "unknown"
    }
}