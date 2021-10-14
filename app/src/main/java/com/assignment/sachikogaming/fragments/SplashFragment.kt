package com.assignment.sachikogaming.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.assignment.sachikogaming.utils.NetworkUtil
import com.assignment.sachikogaming.viewmodel.PostViewModel
import com.google.android.material.snackbar.Snackbar
import com.rusty.sachikogaming.R
import com.rusty.sachikogaming.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private val postVM by hiltNavGraphViewModels<PostViewModel>(R.id.app_nav_graph)
    private lateinit var splashBinding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        splashBinding = FragmentSplashBinding.inflate(layoutInflater)
        return splashBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            requestPostData()
        }
        splashBinding.btnRetry.setOnClickListener {
            showHideViews(View.VISIBLE,View.GONE)
            requestPostData()
        }
    }

    private fun requestPostData() {
        if (NetworkUtil.checkNetworkConnection()) {
            getResponseAndPerformNavigation()
        }else{
            showHideViews(View.GONE,View.VISIBLE)
        }
    }

    private fun getResponseAndPerformNavigation(){
        postVM.getAllPostResponse{
            if(it){
                requireActivity().runOnUiThread {
                    requireView().findNavController().navigate(
                        SplashFragmentDirections.actionSplashFragmentToHomeFragment(),
                        NavOptions.Builder()
                            .setPopUpTo(
                                R.id.splashFragment,
                                true
                            ).build()
                    )
                }
            }else{
                showHideViews(View.GONE,View.VISIBLE)
            }
        }
    }
    private fun showHideViews(pbVisibility : Int,retryBtnVisibility : Int){
        requireActivity().runOnUiThread {
            splashBinding.progressBar.visibility = pbVisibility
            splashBinding.retryBtnHolder.visibility = retryBtnVisibility
        }
    }
}