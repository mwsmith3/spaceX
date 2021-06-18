package com.mwsmith3.spacex.falcon9.data.network.dto

import com.google.gson.annotations.SerializedName

data class LaunchDto(
    @SerializedName("date_local")
    val launchDate: String,

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("name")
    val name: String,

    @SerializedName("links")
    val links: LinksDto
) {
    data class LinksDto(
        @SerializedName("patch")
        val patch: PatchDto
    ) {
        data class PatchDto(
            @SerializedName("small")
            val small: String?
        )
    }
}
