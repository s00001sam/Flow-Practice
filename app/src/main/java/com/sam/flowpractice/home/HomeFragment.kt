package com.sam.flowpractice.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sam.flowpractice.Util.collectLastFlowStarted
import com.sam.flowpractice.Util.toast
import com.sam.flowpractice.databinding.FragmentHomeBinding
import com.sam.flowpractice.repository.statehandle.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initCollectFlow()
    }

    private fun initView() {
        binding.btnSingleStr.setOnClickListener {
            viewModel.getSingleString()
        }
    }

    private fun initCollectFlow() {
        collectLastFlowStarted(viewModel.singleStrResult) {
            when(it) {
                is State.LoadingState -> showLoading()
                is State.DataState -> {
                    dismissLoading()
                    it.data.toast()
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun dismissLoading() {
        binding.progressBar.visibility = View.GONE
    }
}