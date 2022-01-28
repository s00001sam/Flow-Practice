package com.sam.flowpractice.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sam.flowpractice.BaseFragment
import com.sam.flowpractice.Util.toast
import com.sam.flowpractice.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

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

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initCollectFlow()
    }

    @FlowPreview
    private fun initView() {
        binding.btnSingleStr.setOnClickListener {
            viewModel.getSingleString()
        }
        binding.btnSingleStr2.setOnClickListener {
            viewModel.getSingleString2()
        }
        binding.btnInts.setOnClickListener {
            viewModel.getInts()
        }
        binding.btnIntsLatest.setOnClickListener {
            viewModel.getIntsLatest()
        }
        binding.btnIntsBuffer.setOnClickListener {
            viewModel.getIntsBuffer()
        }
        binding.btnIntsConflate.setOnClickListener {
            viewModel.getIntsConflate()
        }
        binding.btnIntsMap.setOnClickListener {
            viewModel.getIntsMap()
        }
        binding.btnIntsZip.setOnClickListener {
            viewModel.getIntsZip()
        }
        binding.btnIntsCombine.setOnClickListener {
            viewModel.getIntsCombine()
        }
        binding.btnIntsFilter.setOnClickListener {
            viewModel.getIntsFilter()
        }
        binding.btnIntsTransform.setOnClickListener {
            viewModel.getIntsTransform()
        }
        binding.btnIntsFlatMapConcat.setOnClickListener {
            viewModel.getIntsflatMapConcat()
        }
        binding.btnIntsFlatMapMerge.setOnClickListener {
            viewModel.getIntsflatMapMerge()
        }
    }

    private fun initCollectFlow() {
        viewModel.strResult.collectDataState {
            it.toast()
        }
    }
}