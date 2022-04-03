package com.mvvm.task.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.task.R
import com.mvvm.task.adapter.VideoPageAdapter
import com.mvvm.task.databinding.FragmentLayoutBinding
import com.mvvm.task.viewmodel.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class Video : Fragment() {
    private lateinit var binding: FragmentLayoutBinding
    lateinit var videoPageAdapter: VideoPageAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_layout, container, false
        )
        val view: View = binding.getRoot()
        binding.fab.visibility=View.GONE
        initRecyclerView()
        initViewModel()
        return view
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            videoPageAdapter = VideoPageAdapter()
            adapter = videoPageAdapter
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    fun initViewModel() {
        val viewModel:VideoViewModel = ViewModelProvider(this).get(VideoViewModel::class.java)

        lifecycleScope.launchWhenCreated {
            viewModel.getvideoList().collectLatest {
                videoPageAdapter.submitData(it)


            }
        }

            viewModel.getvideoList()



    }


}


