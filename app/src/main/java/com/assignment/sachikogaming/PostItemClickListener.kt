package com.assignment.sachikogaming

import com.assignment.sachikogaming.data.entity.PostResponseItem

interface PostItemClickListener {
    fun onPostItemClickListener(selectedPostItem : PostResponseItem)
}