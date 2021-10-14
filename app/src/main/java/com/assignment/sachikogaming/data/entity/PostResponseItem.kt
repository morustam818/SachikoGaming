package com.assignment.sachikogaming.data.entity

import com.google.gson.annotations.SerializedName

data class PostResponseItem(
    val comment: String,
    @SerializedName("_id")
    val id: String,
    val picture: String,
    val publishedAt: String,
    val title: String
)