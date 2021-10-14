package com.assignment.sachikogaming.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.assignment.sachikogaming.MainActivity
import com.assignment.sachikogaming.utils.NetWorkConnectionCallback
import com.assignment.sachikogaming.utils.NetworkConnections
import com.assignment.sachikogaming.utils.NetworkUtil
import com.assignment.sachikogaming.viewmodel.PostViewModel
import com.rusty.sachikogaming.R
import com.rusty.sachikogaming.databinding.FragmentSplashBinding
import kotlinx.coroutines.GlobalScope
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
            delay(2000)
            requireActivity().runOnUiThread {
                requireView().findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }
}