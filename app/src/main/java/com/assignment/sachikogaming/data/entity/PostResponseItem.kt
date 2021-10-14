package com.assignment.sachikogaming.data.entity

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class PostResponseItem(
    val comment: String,
    @SerializedName("_id")
    val id: String,
    val picture: String,
    val publishedAt: String,
    val title: String
){
    fun getPrefix() : String{
        val splitTitle = title.split(" ")
        var prefix = ""
        splitTitle.forEach {
            prefix += it.substring(0,1).uppercase(Locale.getDefault())
        }
        return prefix
    }
    fun getFormattedDate() : String{
        val splitPublishedTime = publishedAt.split(" ")
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
        val date : Date? = sdf.parse(splitPublishedTime.first())
        val splitDate = date.toString().split(" ")
        return "${splitDate[1]} ${splitDate[2]}, ${splitDate[splitDate.size-1]}"
    }
}