package com.assignment.sachikogaming.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.sachikogaming.repository.PostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepositoryImpl: PostRepositoryImpl
) :ViewModel() {

    fun getAllPostResponse() = viewModelScope.launch(Dispatchers.IO){
        val response = postRepositoryImpl.getAllImages()
        if (response.isSuccessful){
            response.body()?.forEach {
                Log.i( "getAllPostResponse: ","${it.title}")
            }
        }else if (!response.isSuccessful){
            Log.i( "getAllPostResponse: ","Something went wrong")
        }
    }

}