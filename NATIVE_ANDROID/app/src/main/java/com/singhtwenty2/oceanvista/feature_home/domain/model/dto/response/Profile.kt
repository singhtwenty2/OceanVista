package com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response

data class Profile(
    val userId: String,
    val userName: String,
    val name: String,
    val email: String,
    val mobileNumber: String,
    val profilePic: String,
    val notificationPrefrence: String
)
