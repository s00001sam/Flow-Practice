package com.sam.flowpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sam.flowpractice.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun dismissLoading() {
        binding.progressBar.visibility = View.GONE
    }
}