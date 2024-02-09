package com.test.numfacts.services

import com.google.gson.annotations.SerializedName

data class NumServiceData(
    @SerializedName("text")
    val text: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("found")
    val found: Boolean,
    @SerializedName("type")
    val type: String
)
