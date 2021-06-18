package com.mwsmith3.spacex.falcon9.domain.model

import org.joda.time.DateTime

data class Falcon9Launch(
    val name: String,
    val success: Boolean,
    val imageUrl: String?,
    val launchDate: DateTime
)
