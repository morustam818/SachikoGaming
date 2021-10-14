package com.assignment.sachikogaming.repository

import com.assignment.sachikogaming.data.entity.PostResponse
import com.assignment.sachikogaming.data.remote.ApiService
import retrofit2.Response
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PostRepository {
    override suspend fun getPostResponse(): Response<PostResponse> = apiService.getAllImages()
}