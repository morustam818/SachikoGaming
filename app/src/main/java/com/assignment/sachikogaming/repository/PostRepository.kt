package com.assignment.sachikogaming.repository

import com.assignment.sachikogaming.data.entity.PostResponse
import retrofit2.Response

interface PostRepository {
    suspend fun getPostResponse() : Response<PostResponse>
}