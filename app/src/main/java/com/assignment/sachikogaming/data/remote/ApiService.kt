package com.assignment.sachikogaming.data.remote

import com.assignment.sachikogaming.data.entity.PostResponse
import com.assignment.sachikogaming.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getAllImages() : Response<PostResponse>
}