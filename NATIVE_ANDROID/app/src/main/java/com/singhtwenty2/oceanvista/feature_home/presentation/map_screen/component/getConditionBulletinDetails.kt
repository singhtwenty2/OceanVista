package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode

@Composable
fun getConditionBulletinDetails(beach: BeachColorCode): Triple<String, Color, FontWeight> {
    return when (mapConditionColorToDescription(beach)) {
        "excellent" -> Triple(
            """Maritime Conditions: Optimal
            
            • Superior Water Visibility: Crystal-clear conditions with exceptional visibility
            • Optimal Wave Patterns: Ideal wave height and frequency for all activities
            • Maximum Safety Rating: Lifeguard-endorsed conditions
            • Premium Recreation Environment: Perfect for all maritime activities
            • Exceptional Swimming Conditions: Calm waters with optimal temperature
            • Risk Assessment: Minimal environmental hazards present""".trimMargin(),
            Color(0xFF1B5E20),  // Rich Forest Green for excellent
            FontWeight.Normal
        )
        "moderate" -> Triple(
            """Maritime Conditions: Exercise Caution
            
            • Maritime Advisory: Variable conditions detected
            • Wave Analysis: Moderate intensity fluctuations present
            • Safety Protocol Implementation:
              - Reserved for experienced maritime enthusiasts
              - Restricted access for novice participants
            • Atmospheric Conditions: Elevated wind velocities possible
            • Required Protocol: Comprehensive weather assessment mandatory
            • Safety Status: Enhanced monitoring protocols in effect""".trimMargin(),
            Color(0xFFE65100),  // Deep Orange for moderate
            FontWeight.Medium
        )
        "poor" -> Triple(
            """Maritime Conditions: Critical Alert
            
            • Emergency Protocol: Severe maritime warning in effect
            • Environmental Status: Hazardous maritime conditions present
            • Maritime Restrictions Enacted:
              - Aquatic activities prohibited
              - Maritime sports suspended
              - Coastal hazard warnings active
            • Hydrological Alert: Severe current activity detected
            • Wave Status: Dangerous maritime conditions
            • Emergency Protocol: Immediate shore evacuation required""".trimMargin(),
            Color(0xFFB71C1C),  // Deep Red for poor
            FontWeight.Bold
        )
        "else" -> Triple(
            """Maritime Conditions: Status Pending
            
            • Analysis Status: Insufficient maritime data available
            • Safety Protocol: Maximum precautionary measures advised
            • Requirements: Professional maritime assessment pending
            • Activity Status: Maritime activities temporarily suspended
            • Authorization: Official clearance required prior to engagement""".trimMargin(),
            MaterialTheme.colorScheme.secondary,
            FontWeight.Bold
        )
        else -> Triple(
            """Maritime Conditions: Unverified
            
            • Status Alert: Maritime conditions undetermined
            • Safety Protocol: Maximum caution advised
            • Required Action: Await official maritime authority guidance
            • Activity Status: Water engagement prohibited
            • Protocol: Additional safety verification required""".trimMargin(),
            MaterialTheme.colorScheme.error,
            FontWeight.Bold
        )
    }
}
