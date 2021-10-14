package com.assignment.sachikogaming.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.assignment.sachikogaming.viewmodel.PostViewModel
import com.bumptech.glide.Glide
import com.rusty.sachikogaming.R
import com.rusty.sachikogaming.databinding.FragmentPostDetailsBinding

class PostDetailsFragment : Fragment() {

    private val postVM by hiltNavGraphViewModels<PostViewModel>(R.id.app_nav_graph)
    private lateinit var mBinding: FragmentPostDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentPostDetailsBinding.inflate(layoutInflater)
        mBinding.postItem = postVM.selectedPost.value
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireView())
            .load(postVM.selectedPost.value?.picture)
            .into(mBinding.ivPost)

        mBinding.ivShare.setOnClickListener {
            shareData()
        }
    }

    private fun shareData() {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            putExtra(Intent.EXTRA_SUBJECT,postVM.selectedPost.value?.title)
            putExtra(Intent.EXTRA_TEXT, postVM.selectedPost.value?.comment)
            requireActivity().startActivity(Intent.createChooser(this,"Share it!"))
        }
    }

}