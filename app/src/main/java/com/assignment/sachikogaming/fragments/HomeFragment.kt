package com.assignment.sachikogaming.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.sachikogaming.PostItemClickListener
import com.assignment.sachikogaming.data.entity.PostResponseItem
import com.assignment.sachikogaming.viewmodel.PostViewModel
import com.google.android.material.snackbar.Snackbar
import com.rusty.sachikogaming.R
import com.rusty.sachikogaming.databinding.FragmentHomeBinding
import java.io.ByteArrayOutputStream
import kotlin.random.Random

class HomeFragment : Fragment(),PostItemClickListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeBinding.rvPost.layoutManager = LinearLayoutManager(requireContext())
        postVM.setRecyclerView(homeBinding.rvPost,this)
        homeBinding.btnAddImage.setOnClickListener {
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCameraActivityForResult()
        } else {
            askForCameraPermission()
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val photo : Bitmap = data?.extras?.get("data") as Bitmap
            photo.let {
                val imagePath = getImagePath(it)
                postVM.postList?.add(0,
                    PostResponseItem(
                        comment = "Last Item data fake data ${postVM.postList?.last()?.comment!!}",
                        id = postVM.postList?.last()?.id!!,
                        picture = imagePath.toString(),
                        publishedAt = postVM.postList?.last()?.publishedAt!!,
                        title = "${postVM.postList?.last()?.title!!}(Fake data)"
                ))
            }
            homeBinding.rvPost.adapter?.notifyDataSetChanged()
        }
    }

    private fun getImagePath(it: Bitmap): Uri {
        val byte = ByteArrayOutputStream()
        it.compress(Bitmap.CompressFormat.JPEG,100,byte)
        val path = MediaStore.Images.Media.insertImage(requireContext().contentResolver,it,"path",null)
        return Uri.parse(path)
    }

    private fun askForCameraPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    private val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                openCameraActivityForResult()
            } else {
                Snackbar.make(requireView(),"Camera Permission required",Snackbar.LENGTH_SHORT).show()
            }
        }


    private fun openCameraActivityForResult() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intent)
    }

    override fun onPostItemClickListener(selectedPostItem: PostResponseItem) {
        postVM.selectedPost.value = selectedPostItem
        requireView().findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToPostDetailsFragment()
        )
    }
}