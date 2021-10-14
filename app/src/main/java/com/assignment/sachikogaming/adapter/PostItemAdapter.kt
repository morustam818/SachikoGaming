package com.assignment.sachikogaming.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.sachikogaming.PostItemClickListener
import com.assignment.sachikogaming.data.entity.PostResponseItem
import com.bumptech.glide.Glide
import com.rusty.sachikogaming.databinding.PostItemLayoutBinding

class PostItemAdapter(
    private val postList : List<PostResponseItem>,
    private val postItemClickListener: PostItemClickListener
) : RecyclerView.Adapter<PostItemAdapter.PostViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBind(postList[position])
    }

    override fun getItemCount() = postList.size

    inner class PostViewHolder(private val itemBinding: PostItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun onBind(postItem : PostResponseItem){
            itemBinding.postItem = postItem
            Glide.with(itemBinding.root)
                .load(postItem.picture)
                .into(itemBinding.ivPost)

            itemBinding.cvPost.setOnClickListener {
                postItemClickListener.onPostItemClickListener(postItem)
            }

        }
    }

}