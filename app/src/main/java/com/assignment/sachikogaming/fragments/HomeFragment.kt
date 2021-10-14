package com.assignment.sachikogaming.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.assignment.sachikogaming.viewmodel.PostViewModel
import com.rusty.sachikogaming.R
import com.rusty.sachikogaming.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val postVM by hiltNavGraphViewModels<PostViewModel>(R.id.app_nav_graph)
    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return homeBinding.root
    }

}