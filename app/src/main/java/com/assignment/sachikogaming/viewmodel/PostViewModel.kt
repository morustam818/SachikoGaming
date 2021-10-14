package com.assignment.sachikogaming.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.assignment.sachikogaming.PostItemClickListener
import com.assignment.sachikogaming.adapter.PostItemAdapter
import com.assignment.sachikogaming.data.entity.PostResponseItem
import com.assignment.sachikogaming.repository.PostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepositoryImpl: PostRepositoryImpl
) :ViewModel() {

    private lateinit var postItemAdapter: PostItemAdapter
    var postList : ArrayList<PostResponseItem>? = null
    var selectedPost = MutableLiveData<PostResponseItem>()

    fun getAllPostResponse(onResult : (Boolean)-> Unit) = viewModelScope.launch(Dispatchers.IO){
        val response = postRepositoryImpl.getAllImages()
        if (response.isSuccessful){
            postList = response.body()
            onResult(true)
        }else if (!response.isSuccessful){
            onResult(false)
        }
    }

    fun setRecyclerView(recyclerView: RecyclerView,postItemClickListener: PostItemClickListener){
        postList?.let {
            postItemAdapter = PostItemAdapter(it,postItemClickListener)
            recyclerView.adapter = postItemAdapter
        }
    }
}